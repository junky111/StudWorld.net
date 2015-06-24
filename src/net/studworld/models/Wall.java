/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.models;


import net.studworld.system.StudWorld;

/**
 * Model Wall with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */
public class Wall extends MongoDBObject {
        
    /*private transient Post post;
    public boolean addPost(Post post, String collection_name){
        post.setWallId(this.getId());
        addToTheWall(this, collection_name);
        return post.create();
    }
    public void updatePost(Post post, String collection_name){
        post.setWallId(this.getId());
        updateTheWallPost(this,collection_name);
        post.save();
    }
    public boolean deletePost(Post post,String collection_name){
        post.setWallId(this.getId());
        removeFromTheWall(this, collection_name);
        return post.delete();
    }
    public void addToTheWall(Wall wall,String collection_name){
        StudWorld.MongoDB.createDocument(StudWorld.MongoDB.getCollection(collection_name),wall);
    }
    public void updateTheWallPost(Wall wall,String collection_name){
        StudWorld.MongoDB.updateDocument(StudWorld.MongoDB.getCollection(collection_name),wall);
    }
    public void removeFromTheWall(Wall wall, String collection_name){
        StudWorld.MongoDB.updateDocument(StudWorld.MongoDB.getCollection(collection_name),wall);
    }

    @Override
    String getCollectionName() {
        return "walls";
    }*/
}
