/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.models;


/**
 * Model PrivateMessage with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */
public class PrivateMessage extends MongoDBObject  {
    private String message, toUserId, fromUserId;

    /**
     *
     */
    public PrivateMessage() {
        this.setCollectionName("pms");
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

   
    /**
     * @return the toUserId
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * @param toUserId the toUserId to set
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * @return the fromUserId
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * @param fromUserId the fromUserId to set
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

}
