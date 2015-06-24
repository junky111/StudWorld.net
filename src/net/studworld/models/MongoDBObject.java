/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.models;

import net.studworld.system.StudWorld;

/**
 * Abstract class MongoDBObject for working with models and writing to the DB.
 * @extends JSONObject
 * @author Ярослав Кузнецов
 */
public abstract class MongoDBObject extends JSONObject {

    /**
     *
     */
    public String _id = "";
    private transient String collectionName = "defaulttable";
    
    /**
     * Creating the document in the database.
     * @param obj  the document to create in the database.
     */
    public static void create(MongoDBObject obj) {
        StudWorld.MongoDB.createDocument(obj);
    }
    
    /**
     * Creating or updating the document in database.
     * @param obj the document to save in the database.
     */
    
    public static void save(MongoDBObject obj) {
        if(StudWorld.MongoDB.persist(obj, true)){
            StudWorld.MongoDB.updateDocument(obj);
        }else{
            StudWorld.MongoDB.createDocument(obj);
        }
    }
    /**
     * Deleting  the document from database.
     * @param obj the document to remove from the database.
     * @return 
     */   
    public static boolean delete(MongoDBObject obj){
        if(StudWorld.MongoDB.persist(obj, true)){
            StudWorld.MongoDB.deleteDocument(obj);
            return true;
        }
        return false;
    }
    
   /**
     * Searching the document in database.
     * @param <T>
     * @param c
     * @param obj the document to seach in the database.
     * @param idOnly
     * @return 
     */
    public static <T extends JSONObject> T search(Class<T> c, MongoDBObject obj, boolean idOnly){
        if(StudWorld.MongoDB.persist(obj, idOnly)){
            return (T)StudWorld.MongoDB.showDocument(c, obj, idOnly);
        }
        return null;
    }
    
    /**
     * 
     * @return collectionName the collectionName to get 
     */
    public String getCollectionName() {
        return this.collectionName;
    }

    /**
     * @param collectionName the collectionName to set
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
