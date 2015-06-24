/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import java.io.IOException;
import net.studworld.models.User;
import net.studworld.packets.p8_User;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class LoadUserLogic class for adding the logic for package, where id=8;
 * @extends LogicMethod
 * @author Ярослав
 */
public class LoadUserLogic extends LogicMethod{
    
    /**
     *
     */
    public LoadUserLogic(){
        this.setId(8);
    }

       /**
     * Execute logic of loading users
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        p8_User p8 = StudWorld.Gson.fromJson(message, p8_User.class);
        p8.setUser(User.search(User.class, p8.getUser(), true));
        StudWorld.RabbitMQ.sendToUser(p8);
    }
    
}
