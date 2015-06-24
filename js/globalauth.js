StudWorld.ready(function() {
    //StudWorld.userHash = StudWorld.generateRandomHash();
    StudWorld.addLogic(AuthResultLogic);
    StudWorld.addLogic(PrivateMessageLogic);
    StudWorld.addLogic(RegenerateHashLogic);
    StudWorld.auth = authFunction;
    StudWorld.backgroundAuth = backgroundAuthFunction;
    StudWorld.register = registerFunction;
    if(StudWorld.getCookie("auth")!==undefined) {
        StudWorld.backgroundAuth(StudWorld.getCookie("auth"));
    }
    //StudWorld.addLogic(new ChatHistoryLogic());
    //StudWorld.addLogic(new RegenerateHashLogic());
});
/* LOGICS */

var AuthResultLogic = new Logic(3, 'AuthResultLogic', function(data) {
    if(data.result) {
        StudWorld.user = data.user;
        StudWorld.setCookie("auth", data.user.uniqueAuthLine, 1000*60*60*24*31);
        UserProfile.load(data.user);
    } else {
        StudWorld.setCookie("auth", "", 0);
    }
});

var PrivateMessageLogic = new Logic(4, 'PrivateMessageLogic', function(data) {
    //$("")
});

var PostPostLogic = new Logic(5, 'PostPostLogic', function(data){
    StudWorld.onEvent('postPost', data);
});

var RegenerateHashLogic = new Logic(12, 'RegenerateHashLogic' ,function(data) {
    if(data.newHash!=="") {
        StudWorld.userHash = data.newHash;
    } else {
        //err
    }
});

var CommentLogic = new Logic(6, 'CommentLogic', function(data){
    StudWorld.onEvent('comment',data);
});
 
var StatusLikeLogic = new Logic(7, 'StatusLikeLogic', function(data){
    StudWorld.onEvent('statusLike',data);
});
 
var ChatHistoryLogic = new Logic(14, 'ChatHistoryLogic',function() {
    StudWorld.onEvent('ChatHistory', data);
});

/* WINDOWS */
/* FUNCTIONS */

var authFunction = function(email, phone, pass) {
    StudWorld.websocket.send(new AuthPacket(email, phone, pass));
};
var backgroundAuthFunction = function(auth) {
    StudWorld.websocket.send(new BackgroundAuthPacket(auth));
};
var registerFunction = function(email, phone, pass, name, surname) {
    StudWorld.websocket.send(new RegisterPacket(email, phone, pass, name, surname));
};