/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.RabbitMQ;

import net.studworld.models.Post;

/**
 *
 * @author Ярослав
 */
public class PostModel extends Post implements IModel{

    /**
     *
     */
    @Override
    public void cache() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param model
     */
    @Override
    public void setCache(IModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
