/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.RabbitMQ;

import net.studworld.models.Group;

/**
 *
 * @author Ярослав
 */
public class GroupModel extends Group implements IModel{

    /**
     *
     * @param name
     * @param img
     * @param description
     */
    public GroupModel(String name, String img, String description) {
        super(name, img, description);
    }

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
