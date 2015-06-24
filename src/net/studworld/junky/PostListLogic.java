/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import java.io.IOException;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class PostListLogic class for adding the logic for package, where id=8;
 * @extends LogicMethod
 * @author Ярослав
 */
public class PostListLogic extends LogicMethod {
    
    /**
     *
     */
    public PostListLogic(){
        this.setId(14);
    }
    
       /**
     * Execute logic of posts list loading 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        
    }
    
}
