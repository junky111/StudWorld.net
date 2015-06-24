/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.RabbitMQ;

import net.studworld.models.Group;
import net.studworld.models.GroupWall;

/**
 *
 * @author Ярослав
 */
public class GroupWallModel extends GroupWall implements IModel{

    /**
     *
     * @param group
     * @param id
     */
    public GroupWallModel(Group group, long id) {
        super(group, id);
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
