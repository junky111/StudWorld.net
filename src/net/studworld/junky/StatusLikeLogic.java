/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import net.studworld.RabbitMQ.PostModel;
import net.studworld.models.Comment;
import net.studworld.models.Post;
import net.studworld.packets.p7_Like;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class StatusLikeLogic class for adding the logic for package, where id=8;
 * @extends LogicMethod
 * @author Ярослав
 */
public class StatusLikeLogic extends LogicMethod {

    /**
     *
     */
    public StatusLikeLogic(){
        this.setId(7);
    }
    
       /**
     * Execute logic of likes adding 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws RabbitMQPushException {
        p7_Like p7 = StudWorld.Gson.fromJson(message, p7_Like.class);
        /*if(p7.action==0){
            PostModel post=(PostModel) new Post();
            post.setId(p7.getObjectLiked());
            if(post.addLike(p7.getUserFrom())){
                p7.self=true;
                StudWorld.RabbitMQ.sendToUser(p7);
            }else{
                p7.self=false;
                StudWorld.RabbitMQ.sendToUser(p7);
            }
        }
        if(p7.action==1){
            Comment comment=new Comment();
            comment.setId(p7.getObjectLiked());
            if(comment.getLike().addLike(p7.getUserFrom())){
                p7.self=true;
                StudWorld.RabbitMQ.sendToUser(p7);
            }else{
                p7.self=false;
                StudWorld.RabbitMQ.sendToUser(p7);
            }
        }
        */
        /// не знаю, как сделать на врешнюю страницу (у меня нет для этого моделей или я просто до конца не представляю это...)
    }
    
}
