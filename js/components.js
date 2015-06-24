

/* COMPONENTS */


var Component = function(url1, callback) {
    this.f = callback;
    this.url = url1;
    this.data = null;
    this.load = function(data) {
        var p = this;
        p.data = data;
        $.ajax({
            url: p.url
        }).done(function(pagedata) {
            p.f($(pagedata), p.data);
        });
    };
};
/*


                .text("Sign in")
                .click(function() {
                    
                })
            )
            .append(
                .text("Register")
                .click(function() {
                    
                })
            )
        );
    };

var RegisterForm1 = new Component('RegisterForm1');
    RegisterForm1.content = function() {
        return $('<div></div>')
        .append(
            $('<div></div>')
            .addClass("auth-logo")
        ).append(
            $('<div></div>')
            .addClass("auth-form")
            .append(
                $('<input/>')
                .addClass("register-emailfield")
                .addClass("authform-field")
                .attr("placeholder","email")
            )
            .append(
                $('<input/>')
                .addClass("register-phonefield")
                .addClass("authform-field")
                .attr("placeholder","phone")
            )
            .append(
                $('<input/>')
                .addClass("register-passfield")
                .addClass("authform-field")
                .attr("type","password")
                .attr("placeholder","password")
            )
            .append(
                $('<div></div>')
                .addClass("auth-login")
                .attr("type","password")
                .attr("placeholder","password")
                .text("Sign in")
                .click(function() {
                    $('#slider').data('rhinoslider').next($('.slide-auth'));
                })
            )
            .append(
                $('<div></div>')
                .addClass("auth-register")
                .text("Register")
                .click(function() {
                    $('#slider').data('rhinoslider').next($('.slide-registration2'));
                })
            )
        );
    };

var RegisterForm2 = new Component('RegisterForm2');
    RegisterForm2.content = function() {
        return $('<div></div>')
        .append(
            $('<div></div>')
            .addClass("auth-logo")
        ).append(
            $('<div></div>')
            .addClass("auth-form")
            .append(
                $('<input/>')
                .addClass("register-name")
                .addClass("authform-field")
                .attr("placeholder","name")
            )
            .append(
                $('<input/>')
                .addClass("register-surname")
                .addClass("authform-field")
                .attr("placeholder","surname")
            )
            .append(
                $('<div></div>')
                .addClass("auth-login")
                .attr("type","password")
                .attr("placeholder","password")
                .text("Sign in")
                .click(function() {
                    $('#slider').data('rhinoslider').next($('.slide-auth'));
                })
            )
            .append(
                $('<div></div>')
                .addClass("auth-register")
                .text("Register")
                .click(function() {
                    StudWorld.register($(".register-emailfield").val(), $(".register-phonefield").val(), $(".register-passfield").val(), $(".register-name").val(), $(".register-surname").val());
                })
            )
        );
    };
var UserData = new Component('UserData');
    UserData.content = function() {
        return $('<div></div>')
        .addClass('user-data');
    };
var UserInteract = new Component('UserInteract');
    UserInteract.content = function(userData) {
        return $('<div></div>')
        .addClass('user-interact')
        .append(
            $('<div></div>')
            .addClass('info-block')
            .append(
                $('<div></div>')
                .addClass('list')
                .append(
                    $('<a></a>')
                    .append(
                       $('<img/>')
                       .attr('src', 'img1')
                    )
                    .append(
                       StudWorld.getT('about-user')
                    )
                    .click(function() {
                        StudWorld.interface.currentWindow.onEvent('about');
                    })
                )
            )
            .append(
                $('<div></div>')
                .addClass('list')
                .append(
                    $('<a></a>')
                    .append(
                       $('<img/>')
                       .attr('src', 'img1')
                    )
                    .append(
                       StudWorld.getT('subscribe')
                    )
                    .click(function() {
                        StudWorld.interface.currentWindow.onEvent('subscribe')
                    })
                )
            )
            .append(
                $('<div></div>')
                .addClass('list')
                .append(
                    $('<a></a>')
                    .append(
                       $('<img/>')
                       .attr('src', 'img2')
                    )
                    .append(
                       StudWorld.getT('send-private-message')
                    )
                    .click(function() {
                        StudWorld.chatHistory(userData.user.id);
                        StudWorld.interface.openWindow('ChatWindow');
                    })
                )
            )
            .append(
                $('<div></div>')
                .addClass('list')
                .append(
                    $('<a></a>')
                    .append(
                       $('<img/>')
                       .attr('src', 'img3')
                    )
                    .append(
                       StudWorld.getT('wall')
                    )
                    .click(function() {
                        StudWorld.interface.currentWindow.onEvent('askWall');
                    })
                )
            )
        );
    };
var UserProfileComponent = new Component('UserProfileComponent');
    UserProfileComponent.addEvent('ChatHistory', function(data) {
        StudWorld.interface.openWindow('ChatComponent');
        StudWorld.interface.currentWindow.user = data.userFrom;
        StudWorld.interface.currentWindow.onEvent('ChatHistory',data);
    });
    UserProfileComponent.activate = function(userData) {
        $("body").css("background-image", 'url('+userData.user.background+')');
        $("body").css("background-image", 'url('+userData.user.background+')');
        StudWorld.interface.main.append(
            $('<div></div>')
            .addClass("user-title")
            .append(
                $('<h1></h1>')
                .addClass("name-label")
                .append(
                    userData.user.name
                )
                .append(
                    userData.user.nickname!==undefined ? " <font class='nickname'>"+userData.user.nickname+"</font> " : " "
                )
                .append(
                    userData.user.surname
                )
            )
        );
        StudWorld.interface.main.append(
            $('<div></div>')
            .addClass('user')
            .append(
                $('<div></div>')
                .addClass("basic-info")
                .append(
                    $('<div></div>')
                    .addClass("photo")
                    .append(
                        $('<img/>')
                        .attr("src", userData.user.avatar)
                    )
                )
                .append(
                    $('<button></button>')
                    .addClass("profile-edit")
                    .html(StudWorld.getT('edit-profile'))
                    .submit(function() {
                        StudWorld.interface.openWindow('EditProfile', null);
                    })
                )
                .append(
                    $('<p></p>')
                    .addClass("about")
                    .append(
                        $('<span></span>')
                        .addClass('friends-count')
                        .html(
                            userData.user.friendsCount + ' ' + StudWorld.getT('friends')
                        )
                    )
                    .append(' | ')
                    .append(
                        $('<span></span>')
                        .addClass('last-online')
                        .html(
                            (userData.user.lastOnline > (Date.now() / 1000 | 0)-60*10) ? StudWorld.getT('user-online') : StudWorld.getT('user-offline-since') + ' ' + new Date(userData.user.lastOnline * 1000)
                        )
                    )
                    .append(
                        userData.user.nickname!="" ? "<span class='nickname'>"+userData.user.nickname+"</span> " : ""
                    )
                )
            )
            .append(
                $('<div></div>')
                .addClass('detail')
                .append(
                    UserInteract.content()
                )
                .append(
                    UserData.content()
                )
            )
        );
    };
    
var ChatTextboxComponent = new Component('ChatTextboxComponent');
    ChatTextboxComponent.content = function(userTo) {
        return $('<div></div>')
        .addClass('textarea-box')
        .append(
            $('<textarea></textarea>')
        )
        .append(
            $('<button></button>')
            .submit(function() {
                StudWorld.PM(userTo.id, $('.textarea-box').val());
            })
        );
    };

var ChatComponent = new Component('ChatComponent');
    ChatComponent.addEvent('ChatHistory', function(data) {
        var messageshtml = '';
        var userTo = ChatComponent.user;
        for(var i = 0; i < data.messages.length; i++) {
            var message = $('<div></div>')
                .addClass('message');
            if(data.messages[i].toUserId!==StudWorld.user.id) {
                message.addClass('self');
                message.append(
                   $('<img/>')
                   .attr('src', StudWorld.user.avatar)
                );
            } else {
                message.append(
                   $('<img/>')
                   .attr('src', userTo.avatar)
                );
            }
            message.append(
                $('<span><span/>')
                .append(
                    data.messages[i].message
                )
            );
            messageshtml += message;
        }
        $(".chat-window").prepend(messageshtml);
    });
    ChatComponent.activate = function() {
        StudWorld.interface.main.append(
            $('<div></div>')
            .addClass('chat-window')
            .append(
                ChatTextboxComponent.content()
            )
        );
    };

var MessageArea = new Component('MessageAreaForm');
MessageArea.content = function(){
    return $('<div></div>')
        .append(
        $('<div></div>')
            .addClass("message-form")
            .append(
            $('<textarea></textarea>')
                .addClass("messageform-input"))
            .append(
            $('<div></div>>')
                .addClass("messageform-userInfo-form")
                .append(
                $('<img>')
                    .addClass("userInfoForm-avatar")
            )
                .append(
                $('<div></div>')
                    .addClass("userInfoForm-nickname")
                    .text("nickname"))
        )
            .append(
            $('<input/>')
                .addClass("messageform-sendButton")
                .attr("type","button")
                .text("Send")
        )
    );
};

var ChatWindow = new Window('ChatWindow');
    ChatWindow.activate = function() {
        ChatComponent.activate();
    };
        
var News = new Window('News');  
        News.addEvent('openNews', function(packet){
             var posts = "";
             for(var i = 0; i < packet.posts.length; i++) {
                posts += $('<div></div>')
                .addClass('news-post')
                .append(
                    $('<div></div>')
                    .addClass('post-title')
                    .append(
                        $('<img/>')
                        .attr('src', packet.posts[i].user.avatar)
                    )
                    .append(
                        $('<p></p>')
                        .text(packet.posts[i].user.name + ' ' + packet.posts[i].user.surname)
                    )
                )
                .append(
                    $('<div></div>')
                    .addClass('post-content')
                    .append(
                        $('<h3></h3>')
                        .text(packet.posts[i].content)
                    )
                );
        }
        $(".user .user-data").append(
            posts
        );
        }
        );
       News.addEvent('addPost', function(packet){
           StudWorld.postPost(packet.userId, packet.wallId, packet.content);
       });

/*var UserProfile = new Window('UserProfile');
    UserProfile.user = null;
    UserProfile.addEvent('loadedUser', function(data) {
        StudWorld.interface.currentWindow.onEvent('askWall', data.user._id);
        StudWorld.interface.openWindow('UserProfile');
    });
    UserProfile.addEvent('askWall', function(userId) {
        StudWorld.loadWallHistory(userId);
    });
    UserProfile.addEvent('openWall', function(packet) {
        var posts = "";
        for(var i = 0; i < packet.posts.length; i++) {
            posts += $('<div></div>')
                .addClass('blog-post')
                .append(
                    $('<div></div>')
                    .addClass('post-title')
                    .append(
                        $('<img/>')
                        .attr('src', UserProfile.user.avatar)
                    )
                    .append(
                        $('<p></p>')
                        .text(UserProfile.user.name + ' ' + UserProfile.user.surname)
                    )
                )
                .append(
                    $('<div></div>')
                    .addClass('post-content')
                    .append(
                        $('<h3></h3>')
                        .text(packet.posts[i].content)
                    )
                );
        }
        $(".user .user-data").append(
            posts
        );
    });
    UserProfile.activate = function(userData) {
        UserProfile.user = userData.user;
        UserProfile.addEvent('askWall');
        UserProfileComponent.activate(userData);
    };

var AuthWindow = new Window('AuthWindow');
    AuthWindow.addEvent('successAuth', function(userId) {
        StudWorld.loadUser(userId);
    });
    AuthWindow.activate = function() {
        //StudWorld.interface.main;
        var par = $('')
        .addClass("slider-parent")
        .append(
            $('<img/>')
            .addClass("auth-logo")
            .attr('src', '/images/main/ful-logo.jpg')
        );
        var sl = $('<div></div>')
                .addClass("auth-slider")
                .attr("id", "slider");
        sl.append($('<li></li>')
            .addClass("slide-wait")
            .append(
                
            )
        );
        sl.append($('<li></li>')
            .addClass("slide-auth")
            .append(
                AuthForm.content()
            )
        );
        sl.append($('<li></li>')
            .addClass("slide-registration1")
            .append(
                RegisterForm1.content()
            )
        );
        sl.append($('<li></li>')
            .addClass("slide-registration2")
            .append(
                RegisterForm2.content()
            )
        );
        par.append(sl);
        StudWorld.interface.main.append(par);
        $('#slider').rhinoslider({
            easing: 'easeInOutQuart',
            effectTime: 500,
            controlsMousewheel: false,
            controlsKeyboard: false,
            controlsPrevNext: false,
            controlsPlayPause: false,
            animateActive: false,
            showBullets: 'never',
            showControls: 'never'
        });
    };
    AuthWindow.deactivate = function() {
        $(".slider-parent").remove();
    };
    
*/