/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.RabbitMQ;

import net.studworld.models.User;

/**
 *
 * @author Ярослав
 */
public class UserModel extends User implements IModel{


    /*public boolean userReg(){
        if(this.isUniq(this.getEmail())){
            this.create();
             return true;
        }
        return false;   
    }*/

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
