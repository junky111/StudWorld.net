function AuthPacket(email, phone, pass){
    this.id = 2;
    this.email = email;
    this.phone = phone;
    this.pass = pass;
    this.connection = false;
    
    /*Getters and setters*/
}
AuthPacket.prototype={
  getId:function(){
      return this.id;
  } ,
  getEmail : function(){
      return this.email;
  },
  setEmail : function(email){
      this.email=email;
  },
  getPhone : function(){
      return this.phone;
  },
  
  setPhone : function(phone){
      this.phone=phone;
  },
  
  getPass: function(){
      return this.pass;
  },
  
  setPass : function(pass){
      this.pass=pass;
  } 
};

function ChatHistoryPacket(id){
    this.id = 11;
    this.userTo = id;
    this.userFrom = StudWorld.user._id; 
}

ChatHistoryPacket.prototype={
  getId : function(){
      return this.id;
  }  ,
  getUserTo : function(){
      return this.userTo;
  },
  setUserTo : function(userTo){
      this.userTo = userTo;
  },
  getUserFrom : function(){
      return this.userFrom;
  },
  setUserFrom :  function(userFrom){
      this.userFrom=userFrom;
  }
};

function CommentPacket(userId,creationTime,content,destinationId){
    this.id=6;
    this.userId=userId, 
    this.creationTime=creationTime;
    this.content=content;
    this.destinationId=destinationId;
};

CommentPacket.prototype={
    getId : function(){
        return this.id;
    },
    getUserId : function(){
        return this.userId;
    },
    setUserId : function(userId){
        this.userId=userId;
    },
    getCreationTime : function(){
        return this.creationTime;
    },
    setCreationTime : function(creationTime){
        this.creationTime=creationTime;
    },
    getContent : function(){
        return this.content;
    },
    setContent:  function ( content){
        this.content=content;
    }
};
    

function LoadCommentsPacket(comments){  /*comments - массив, в котором будут хранится стек комментов в определенном количестве (будет отправляться пачками)*/
    this.id=16;
    this.comments=comments;
};

LoadCommentsPacket.prototype={
    getId : function(){
        return this.id;
    },
    getComments : function(){
        return this.comments;
    },
    setComments :  function(comments){
        this.comments=comments;
    }
};

function PostPostPacket(userId1,content1){
    this.id=5;
    this.post={userId:userId1, content:content1};   
 };

function PrivateMessagePacket(toUserId1, message1){
    this.id = 4;
    this.message = {
        toUserId:toUserId1,
        fromUserId:StudWorld.user._id,
        message:message1
    };
};

PrivateMessagePacket.prototype={
   getId : function(){ 
       return this.id;
   },
   getFromUserId:function(){
       return this.fromUserId;
   },
   getToUserId  : function(){
       return this.toUserId;
   },
   setToUserId : function(toUserId){
       this.toUserId=toUserId;
   },
   getMessage : function(){
       return this.message;
   },
   setMessage : function(message){
       this.message=message;
   }
};

function RegenerateHashPacket(hash){
    this.id = 12;
    this.newHash = hash;
};

RegenerateHashPacket.prototype={
    getId:function(){
        return this.id;
    },
    getNewHash : function(){
        return this.newHash;
    },
    setNewHash:function(newHash){
        this.newHash=newHash;
    }
};

function RegisterPacket(email, phone, pass, name, surname){
    this.id = 1;
    this.email = email;
    this.phone = phone;
    this.pass = pass;
    this.name = name;
    this.surname = surname;
    this.connection = false;
};

RegisterPacket.prototype={
    getId: function(){
        return this.id;
    },
    getEmail : function(){
        return this.email;
    },
    setEmail:function(email){
        this.email=email;
    },
    getPhone : function(){
        return this.phone;
    },
    setPhone:function(phone){
        this.phone=phone;
    },
    setPass:function(pass){
        this.pass=pass;
    },
    getPass: function(){
        return this.pass;
    },
    getName: function(){
        return this.name;
    },
    setName:function(name){
        this.name=name;
    },
    getSurname : function(){
        return this.surname;
    },
    setSurname:function(surname){
        this.surname=surname;
    }
}

function UserPacket(userId, bg1){
    this.id=8;
    this.background = bg1;
    this.user = {_id:userId};
};

UserPacket.prototype={
    getId: function(){
        return this.id;
    },
    getFriendsCount : function(){
        return this.friendsCount;
    },
    setFriendsCount:function(friendsCount){
        this.friendsCount=friendsCount;
    },
    getEmail : function(){
        return this.email;
    },
    setEmail:function(email){
        this.email=email;
    },
    getAvatar : function(){
        return this.avatar;
    },
    setAvatar : function(avatar){
        this.avatar= avatar;
    },
    getNickName: function(){
        return this.nickname;
    },
    setNickName: function(nickname){
        this.nickname = nickname;
    },
    getPhoneNumber : function(){
        return this.phoneNumber;
    },
    setPhoneNumber:function(phoneNumber){
        this.phoneNumber=phoneNumber;
    },
    getName: function(){
        return this.name;
    },
    setName:function(name){
        this.name=name;
    },
    getSurname : function(){
        return this.surname;
    },
    setSurname:function(surname){
        this.surname=surname;
    }
}

function StatusLikePacket(userFrom, objectLiked, itemType){
    this.id=7;
    this.userFrom=userFrom;
    this.objectLiked=objectLiked;
    this.itemType=itemType;
};

StatusLikePacket.prototype={
    getId:function(){
        return this.id;
    },
    getUserFrom:function(){
        return this.userFrom;
    },
    setUserFrom:function(userFrom){
        this.userFrom=userFrom;
    },
    getObjectLiked:function(){
        return this.objectLiked;
    },
    setObjectLiked:function(objectLiked){
        this.objectLiked=objectLiked;
    },
    getItemType:function(){
        return this.itemType;
    },
    setItemType :function(itemType){
        this.itemType=itemType;
    }
};

function SubscribesListPacket(subId){     
    this.id=13;
    this.subId=subId;
    this.sub=[];
};

SubscribesListPacket.prototype={
    getId:function(){
        return this.id;
    },
    getSubId: function(){
        return this.subId;
    },
    setSubId:function(subId){
        this.subId=subId;
    },
    getSub_array:function(){
        return this.sub_array;
    },
    setSub_array:function(sub_array){
        this.sub_array=sub_array;
    }
};


function SubscribeUserPacket(userId,toUserId, toGroupId,state){
    this.id=9;
    this.userId=userId;
    this.toUserId=toUserId;
    this.toGroupId=toGroupId;
    this.state=state;    
};

SubscribeUserPacket.prototype={
    getId:function(){
        return this.id;
    },
    getUserId:function(){
        return this.userId;
    },
    setUserId:function(userId){
        this.userId=userId;
    },
    getToUserId:function(){
        return this.toUserId;
    },
    setToUserId:function(toUserId){
        this.toUserId=toUserId;
    },
    getToGroupId:function(){
        return this.toGroupId;
    },
    setToGroupId:function(toGroupId){
        this.toGroupId=toGroupId;
    },
    getState:function(){
        return this.state;
    },
    setState:function(state){
        this.state=state;
    }
};


function PhotosListPacket(userId){     
    this.id=17;
    this.uId=userId;
    this.photos=[];
};
function PostsListPacket(userId){     
    this.id=14;
    this.uId=userId;
    this.posts=[];
};
function PrivateMessagesPacket(userId){     
    this.id=11;
    this.fromUserId = userId;
    this.pms=[];
};
function BackgroundAuthPacket(auth){
    this.id = 18;
    this.auth = auth;
}