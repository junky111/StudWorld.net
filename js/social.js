StudWorld.ready(function() {
    StudWorld.addLogic(LoadUserLogic);
    StudWorld.addLogic(LoadWallHistoryLogic);
    StudWorld.addLogic(LoadSubscribersLogic);
    StudWorld.addLogic(LoadPhotosLogic);
    StudWorld.addToCache(true, 3, function() {return true;}, function(data) {
        LeftMenu.load(data.user);
    });
    StudWorld.addToCache(true, 8, function() {return true;}, function(data) {
        $("body .user"+data._d+" img.avatar").attr("src", data.avatar);
        $("body .user"+data._d+" .name").text(data.name);
    });
});
/* LOGIC */

var LoadUserLogic = new Logic(8, 'LoadUserLogic', function(data) {
    UserProfile.load(data.user);
});
var LoadWallHistoryLogic = new Logic(11, 'LoadWallHistoryLogic', function(data) {
    PrivateMessages.load(data);
});
var LoadSubscribersLogic = new Logic(13, 'LoadSubscribersLogic', function(data) {
    SubscribersList.load(data);
});
var LoadPhotosLogic = new Logic(17, 'LoadPhotosLogic', function(data) {
    PhotosList.load(data);
});
StudWorld.openUser = function(id) {
    StudWorld.websocket.send(new UserPacket(id, false));
};
StudWorld.loadUser = function(id) {
    StudWorld.websocket.send(new UserPacket(id, true));
};
StudWorld.loadPosts = function(userId) {
    StudWorld.websocket.send(new PostsListPacket(userId));
};
StudWorld.loadPhotos = function(userId) {
    StudWorld.websocket.send(new PhotosListPacket(userId));
};
StudWorld.openPrivateMessages = function(userId) {
    StudWorld.websocket.send(new PrivateMessagesPacket(userId));
};
StudWorld.loadSubscribes = function(userId) {
    StudWorld.websocket.send(new SubscribesListPacket(userId));
};
    
StudWorld.postPost = function (userId,content) {
    StudWorld.websocket.send(new PostPostPacket(userId,content));
 };
    
StudWorld.comment= function (userId,creationTime,content,destinationId ){
    StudWorld.websocket.send(new CommentPacket(userId,creationTime,content,destinationId));
};
    
StudWorld.statusLike = function (userFrom, objectLiked, itemType){
    StudWorld.websocket.send(new StatusLikePacket(userFrom, objectLiked, itemType));
};

var UserProfile = new Component('/ru/social/user', function(page, data) {
    page.find(".leftpart > .avatar > img").attr("src", data.avatar);
    page.find(".leftpart #name").text(data.name + ' ' + data.surname);
    page.find(".leftpart #personal").text(StudWorld.getT("Personal information")+ " »");
    if(StudWorld.user==null) {
        page.find(".leftpart #pm").remove();
        page.find(".writewall").remove();
    } else {
        var writewall = page.find(".writewall");
        writewall.find(".header").text(StudWorld.getT("Blog post"));
        writewall.find(".send").text(StudWorld.getT("Send")).click(function() {
            var id = data._id;
            StudWorld.postPost(id, $("#posttext").val());
            $("#posttext").val("");
        });
        page.find(".leftpart #pm").text(StudWorld.getT("Personal message")+ " »").click(function() {
            var id = data._id;
            StudWorld.openPrivateMessages(id);
        });
    }
    
    StudWorld.loadSubscribes(data._id);
    StudWorld.loadPhotos(data._id);
    StudWorld.loadPosts(data._id);
    window.history.pushState('user'+data._id, data.name + ' ' + data.surname, '/ru/social/user?id='+data._id);
    $("body #main-container").html(page);
});

var SubscribersList = new Component('/ru/social/subscribers', function(subscribers, data) {
    subscribers.find("header").text(StudWorld.getT("Subscribers"));
    var subscriber = subscribers.find(".subscriber").clone();
    subscribers.find(".line .subscriber").remove();
    var subscribersLine = subscribers.find(".line").clone();
    if(data.length===0) {
        subscribers.find(".line").text("no subscribers for now");
    } else {
        for(var i = 0; i < 3; i++) {
            var s = subscriber.clone();
            s.find(".subscriber-avatar").attr("src", "");
        }
    }
    $("body #main-container .subscribers").html(subscribers);
});

var PhotosList = new Component('/ru/social/photos', function(photos, data) {
    photos.find("h4").text(StudWorld.getT("Photos"));
    var photo = photos.find(".photo").clone();
    photos.find(".photo").remove();
    var photoline = photos.find(".photoline").clone();
    photos.find(".photoline").remove();
    var lines = 1;
    var photosPerLine = 5;
    if(data.photos.length===0) {
        photos = $("<div></div>")
            .text(StudWorld.getT("User dont have photos"))
            .addClass('nophotos');
        lines = 0;
    } else if(data.photos.length===10) {
        lines = 2;
    }
    for(var i2 = 0; i2 < lines; i2++) {
        var currentLine = photoline.clone();
        for(var i1 = 0 + photosPerLine*i2; i1 < photosPerLine*i2 + photosPerLine; i1++) {
            if(data.photos[i1]===undefined) continue;
            var currentPhoto = photo.clone();
            if(data.photos[i1].name!="") {
                currentPhoto.find(".sign").text(data.photos[i1].name);
            } else {
                currentPhoto.find(".sign").remove();
            }
            currentPhoto.find("img").attr("src", data.photos[i1].url);
            currentLine.append(currentPhoto);
        }
        photos.append(currentLine);
    }
    console.log(photos);
    $("body #main-container .photos").html(photos);
});

var PostsList = new Component('/ru/social/posts', function(posts, data) {
    var post = posts.find(".post").clone();
    posts.find(".post").remove();
    posts.find(".header").text(StudWorld.getT("Wall"));
    for(var i2 = 0; i2 < data.posts.length; i2++) {
        var p = post.clone();
        StudWorld.loadUser(data.uId);
        p.find(".content").text(data.posts[i2].content);
        p.find("#time").text(data.posts[i2].text());
        posts.append(p);
    }
    $("body #main-container .posts").html(posts);
});

var LeftMenu = new Component('/ru/social/leftmenu', function(page, data) {
    page.find(".home").click(function() {StudWorld.openUser(data._id)});
    page.find(".messages").click(function() {StudWorld.openPrivateMessages(data._id)});
    page.find(".news").click(function() {StudWorld.openNews(data._id)});
    $("body").append(page);
});

var PrivateMessages = new Component('/ru/social/pms', function(pagedata, data) {
    window.history.pushState('user'+data.fromUserId, "", '/ru/social/pms');
    var page = $(pagedata);
    page.find("#receiver").attr("data-userId", data.fromUserId);
    page.find(".send").text(StudWorld.getT("Send"));
    var personal = page.find(".pm-personal").clone();
    var external = page.find(".pm-external").clone();
    page.find(".pm-personal").remove();
    page.find(".pm-external").remove();
    page.find(".send").click(function() {
        StudWorld.PM($("#receiver").attr("data-userId"), $("#pmarea").val());
        $("#pmarea").val("");
    });
    var messages = page.find(".messages");
    for(var i = 0; i < data.pms.length; i++) {
        if(data.pms[i].fromUserId==StudWorld.user._id) {
            var m = personal.clone();
        } else {
            var m = external.clone();
        }
        m.find("span").text(data.pms[i].message);
        messages.append($("<div class='pm'></div>").html(m));
    }
    $("body #main-container").html(page);
});