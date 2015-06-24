/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.models;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import net.studworld.system.MongoDBJDBC;


/**
 * Model SubscribeList with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */
public class SubscribsList extends MongoDBObject{
    private Group group = new Group();
    MongoDBJDBC mg = new MongoDBJDBC();
    private User user = new User();
    private String userId;
    private ArrayList<String> subList = new ArrayList<>();

    /**
     *
     * @param subList
     */
    public SubscribsList(ArrayList<String> subList){
        this.subList=subList;
    }
    
    /*public ArrayList<String> formSubList(String userId,MongoDBJDBC mongo){
        
        DBCursor cursor = mongo.getCollection(this.getGroup().getCollectionName()).find((DBObject) JSON.parse("subscribers "),
                new BasicDBObject("$in", new BasicDBList().add(userId)));
        DBCursor cursor2 = mongo.getCollection(this.getUser().getCollectionName()).find((DBObject) JSON.parse("subscribers "),
                new BasicDBObject("$in", new BasicDBList().add(userId))).;
        

    }
*/

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

    /**
     * @return the subList
     */
    public ArrayList<String> getSubList() {
        return subList;
    }

    /**
     * @param subList the subList to set
     */
    public void setSubList(ArrayList<String> subList) {
        this.subList = subList;
    }

    /**
     * @return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
