/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.models;


/**
 * Model Subscribe  with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */
public class Subscribe extends MongoDBObject {
    private static transient short SUBSCRIBED = 0;

    /**
     *
     */
    public static final transient short UNSUBSCRIBED = 1;

    /**
     * @return the SUBSCRIBED
     */
    public static short getSUBSCRIBED() {
        return SUBSCRIBED;
    }

    /**
     * @param aSUBSCRIBED the SUBSCRIBED to set
     */
    public static void setSUBSCRIBED(short aSUBSCRIBED) {
        SUBSCRIBED = aSUBSCRIBED;
    }

    
    private String userId,toUserId, toGroupId;
    private short state;

    /**
     *
     */
    public Subscribe() {
        this.setCollectionName("subscribes");
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
     * @return the toGroupId
     */
    public String getToGroupId() {
        return toGroupId;
    }

    /**
     * @param toGroupId the toGroupId to set
     */
    public void setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
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
}
