/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.models;

import java.util.ArrayList;

/**
 * Model Like. Class for working with likes.
 * @author Ярослав Кузнецов
 */
public class Like {
    private ArrayList likes;
    
    /**
     *
     * @param id
     */
    public Like(long id){
        this.addLike(id);
    }
    
    /**
     * 
     * @param id  userFrom like.
     * @return  boolean , added or not.
     */
    public boolean addLike(long id){
        return likes.add(id);
    }
    
   /**
     * 
     * @param id  userFrom like.
     * @return  boolean ,removed or not.
     */
    public boolean removeLike(long id){
        return likes.remove(id);
    }
    /**
     * @return the likes
     */
    public ArrayList getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(ArrayList likes) {
       this.likes=likes;
    }
}
