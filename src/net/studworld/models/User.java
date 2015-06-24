package net.studworld.models;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import net.studworld.system.StudWorld;



/**
 * Model User with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */

public class User extends MongoDBObject  {
    
    public final transient static short
            GUEST = 0,

    /**
     *
     */
    REGISTERED = 1,

    /**
     *
     */
    ONLINE = 2,

    /**
     *
     */
    OFFLINE = 3,

    /**
     *
     */
    LOGGED_OUT = 4,

    /**
     *
     */
    LOGGED_IN = 5;
    
    
    public final transient static short
            ROLE_GUEST = 0,

    /**
     *
     */
    ROLE_USER = 1,

    /**
     *
     */
    ROLE_AUTHOR = 2,

    /**
     *
     */
    ROLE_MODERATOR = 3,

    /**
     *
     */
    ROLE_ADMIN = 4,

    /**
     *
     */
    ROLE_ANGEL = 5;
    
    private String avatar, background;
    private String friendsCount;
    private String name, surname, nickname, email, phoneNumber, pass;
    private transient short roleId = User.ROLE_GUEST;
    private transient short state = User.GUEST;

    private transient ArrayList<Subscribe> subscribes = new ArrayList<>();

    /**
     *
     */
    public User() {
        this.setCollectionName("users");
    }
    
    
   /** Check the email for the uniq
     * @param email .
     * @return boolean. Is email uniq.
     */
    public boolean isUniq(String email){
        DBCollection collection = StudWorld.MongoDB.getCollection(this.getCollectionName());
        return (JSONObject) collection.find((DBObject) JSON.parse("{email: "+email+"}")).next()==null;
    }
    
    /**
     *
     * @return
     */
    public boolean isAuth(){
        return this.state == 5;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the state
     */
    public short getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(short state) {
        this.state = state;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    /**
     * @return the subscribes
     */
    public ArrayList<Subscribe> getSubscribes() {
        return subscribes;
    }

    /**
     * @return the background
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * @return the friendsCount
     */
    public String getFriendsCount() {
        return friendsCount;
    }

    /**
     * @param friendsCount the friendsCount to set
     */
    public void setFriendsCount(String friendsCount) {
        this.friendsCount = friendsCount;
    }
}
