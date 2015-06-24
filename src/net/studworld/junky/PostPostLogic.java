/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import net.studworld.models.Post;
import net.studworld.packets.p5_Post;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class PostPostLogic class for adding the logic for package, where id=8;
 * @extends LogicMethod
 * @author Ярослав
 */
public class PostPostLogic extends LogicMethod{

    /**
     *
     */
    public PostPostLogic(){
        this.setId(5);
    }
    
       /**
     * Execute logic of posting posts 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws RabbitMQPushException {
        p5_Post p5 = StudWorld.Gson.fromJson(message, p5_Post.class);
        switch(p5.action) {
            case 0:
            case 1:
                Post.save(p5.getPost());
                break;
            case 2:
                p5.result=Post.delete(p5.getPost());
                break;
        }
        StudWorld.RabbitMQ.sendToGroup(1, p5.getPost().getUserId(), p5);
    }
    
}
