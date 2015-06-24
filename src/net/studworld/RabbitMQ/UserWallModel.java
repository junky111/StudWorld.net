/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.RabbitMQ;

import net.studworld.models.User;
import net.studworld.models.UserWall;

/**
 *
 * @author Ярослав
 */
public class UserWallModel extends UserWall implements IModel {

    /**
     *
     * @param user
     * @param id
     */
    public UserWallModel(User user, long id) {
        super(user, id);
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
