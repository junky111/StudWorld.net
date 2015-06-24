/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.models;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import net.studworld.system.MongoDBJDBC;



/**
 * Model Post with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */
public class Post extends MongoDBObject  {
    public static short NEW = 0,    

    /**
     *
     */
    UPDATE = 1,    

    /**
     *
     */
    DELETE = 2;    

    
    private String userId;
    private long wallId;
    private String content;
    private transient User user;
    
    /**
     *
     */
    public Post(){
        this.setCollectionName("posts");
    }
    
        /**
     * Use to take the necessary number of posts of some user.
     * @param mongo
     * @param count. The necessary number of posts.
     * @return  the list of posts.
     */
    public ArrayList<Post> showLastPostsByUser(MongoDBJDBC mongo, int count){
        ArrayList <Post> obj = new ArrayList();
        DBCollection col = mongo.getCollection(getCollectionName());
        DBCursor cursor = col.find((DBObject) JSON.parse("{userId: "+this.user.getId()+"}")).limit(count);
        while(cursor.hasNext()){
            obj.add((Post) cursor.next());
        }
       return obj; 
    }
    


    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the wallId
     */
    public long getWallId() {
        return wallId;
    }

    /**
     * @param wallId the wallId to set
     */
    public void setWallId(long wallId) {
        this.wallId = wallId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
