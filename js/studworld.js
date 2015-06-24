/* SYSTEM */

var Socket = function() {

    this.socket;
    this.sendbuffer = [];
    this.connect = function() {
        this.socket = new WebSocket("ws://"+(StudWorld.getCookie("debug")!==undefined ? "localhost:8084" : "104.236.202.15:8080")+"/web/socket");
        this.socket.onopen = function() { 
            StudWorld.onConnected();
        };
        this.socket.onclose = function(event) {
            setTimeout(
                function () {
                    if (StudWorld.websocket.socket===undefined || StudWorld.websocket.socket.readyState !== 1) {
                        StudWorld.websocket.connect();
                    }
                }, 250); 
        };
        this.socket.onerror = function(error) {};

        this.socket.onmessage = function(event) { 
            if(StudWorld.debug) console.log("[IN] ", event.data);
            StudWorld.parseMessage(event.data);
        };

    };
    this.send = function(packet) {
        if(packet['connection']===undefined) {
            packet.connection = true;
        }
        packet.hash = StudWorld.generateRandomHash();
        packet.logged = false;
        if(StudWorld.user!==null) {
            packet.userId = StudWorld.user._id;
            packet.logged = true;
        }
        this.sendbuffer.push(packet);
        if(this.sendbuffer.length===1) {
            this.startSending();
        }
    };
    this.startSending = function() {
        setTimeout(
            function () {
                if (StudWorld.websocket.socket!==undefined && StudWorld.websocket.socket.readyState === 1) {
                    if(StudWorld.websocket.sendbuffer.length>0) {
                        var obj = StudWorld.websocket.sendbuffer.splice(0,1)[0];
                        if((obj.connection && StudWorld.userHash!=="") || (!obj.connection)){
                            StudWorld.buffer.push(obj);
                            obj.userHash = StudWorld.userHash;
                            if(StudWorld.debug) console.log("[OUT] ", JSON.stringify(obj));
                            StudWorld.websocket.socket.send(JSON.stringify(obj));
                        } else {
                            StudWorld.websocket.sendbuffer.push(obj);
                        }
                    }
                }
                if(StudWorld.websocket.sendbuffer.length>0) StudWorld.websocket.startSending();
            }, 20); 
    };
    setTimeout(
        function () {
            if (StudWorld.websocket.socket===undefined || StudWorld.websocket.socket.readyState !== 1) {
                StudWorld.websocket.connect();
            }
        }, 250); 
};

var Interface = function() {
    this.main = null;
    this.windows = [];
    this.currentWindow = null;
    this.addWindow = function(window) {
        this.windows.push(window);
    };
    this.openWindow = function(name, arguments) {
        for(var i = 0; i < this.windows.length; i++) {
            if(this.windows[i].name===name) {
                this.currentWindow.deactivate();
                this.currentWindow = this.windows[i];
                this.currentWindow.activate(arguments);
                return;
            }
        }
        console.err('WINDOW ' + name + ' NOT FOUND');
    };
};

var System = function() {
    this.websocket = new Socket();
    this.receivelogics = [];
    this.buffer = [];
    this.readyfunc = [];
    this.translations = new Object();
    this.user = null;
    this.personalId = "undefinedid";
    this.userHash = "";
    this.interface = new Interface();
    this.events = new Object();
    this.cache = [];
    this.addToCache = function(permanent1, packetId1, expression1, fn1) {
        var c = {
            permanent: permanent1,
            packetId: packetId1,
            expression: expression1,
            fn: fn1
        };
        this.cache.push(c);
    };
    this.onConnected = function() {};
    this.parseMessage = function(message) {
        var data = JSON.parse(message);
        for(var i = 0; i < StudWorld.receivelogics.length; i++) {
            if(StudWorld.receivelogics[i].id===data.id) {
                //if(StudWorld.debug) console.log("["+StudWorld.receivelogics[i].id+"] "+StudWorld.receivelogics[i].name+" | "+message);
                /*if(data.background) {
                    for(var i1 = 0; i1 < StudWorld.cache.length; i1++) {
                        if(StudWorld.cache[i1].packetId===data.id && StudWorld.cache[i1].expression(data)) {
                            StudWorld.cache[i1].fn(data);
                            if(!StudWorld.cache[i1].permanent) {
                                StudWorld.websocket.sendbuffer.splice(i1,1)[0];
                            }
                            return true;
                        }
                    }
                }*/
                StudWorld.receivelogics[i].execute(data);
                return;
            }
        }
    };
    this.PM = function(toUserId, text) {
        StudWorld.websocket.send(new PrivateMessagePacket(toUserId, text));
    };
    this.chatHistory = function(toUserId) {
        StudWorld.websocket.send(new ChatHistoryPacket(toUserId));
    };
    this.generateRandomHash = function() {
        var text = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for( var i=0; i < 25; i++ )
            text += possible.charAt(Math.floor(Math.random() * possible.length));
        /*for(var q = 0; q < StudWorld.buffer.length; q++) {
            if(q<StudWorld.buffer.length && StudWorld.buffer[q].hash===text) {
                return StudWorld.generateRandomHash();
            }
        }*/
        return text;
    };

    this.addLogic = function(logic) {
        this.receivelogics.push(logic);
    };
    this.start = function() {
        for( var i=0; i<this.readyfunc.length; i++ ) {
            this.readyfunc[i]();
        }
        
    };
    this.ready = function(handle) {
        this.readyfunc.push(handle);
    };
    this.addT = function(name, value) {
        this.translations[name] = value;
    };
    this.getT = function(name) {
        if(this.translations[name]!==undefined) {
            return this.translations[name];
        }
        return name;
    };
    this.addEvent = function(name, callback) {
        this.events[name] = callback;
    };
    this.onEvent = function(name, data) {
        if(this.events[name]!==undefined) {
            this.events[name](data);
        }
    };
    this.getCookie = function(name) {
        var matches = document.cookie.match(new RegExp(
          "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    };
    this.setCookie = function(name, value, expires, path, domain, secure) {
	expires instanceof Date ? expires = expires.toGMTString() : typeof(expires) == 'number' && (expires = (new Date(+(new Date) + expires * 1e3)).toGMTString());
	var r = [name + "=" + escape(value)], s, i;
	for(i in s = {expires: expires, path: path, domain: domain}){
		s[i] && r.push(i + "=" + s[i]);
	}
	return secure && r.push("secure"), document.cookie = r.join(";"), true;
    };

    this.debug = this.getCookie("debug")!==undefined;
};

var Window = function(name) {
    this.name = name;
    this.events = new Object();
    this.activate = function() {};
    this.deactivate = function() {};
    this.addEvent = function(name, callback) {
        this.events[name] = callback;
    };
    this.onEvent = function(name, data) {
        if(this.events[name]!==undefined) {
            this.events[name](data);
        }
    };
};

var Logic = function(id, name, callback) {
    this.id = id;
    this.name = name;
    this.execute = callback;
};

var StudWorld = new System();


var TopMenu = new Component('/ru/topmenu/index', function(pagedata, data) {
    $("body").prepend(pagedata);
});
    
$(document).ready(function() {
    StudWorld.interface.main = $('#main-container');
    StudWorld.ready(function() {
        TopMenu.load();
    });
    StudWorld.start();
});