/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import net.studworld.models.Comment;
import net.studworld.packets.p6_Comment;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class CommentPostLogic class for adding the logic for package, where id=6;
 * @extends LogicMethod
 * @author Ярослав
 */
public class CommentPostLogic extends LogicMethod {

    /**
     *
     */
    public CommentPostLogic(){
        this.setId(6);
    }
    
       /**
     * Execute logic of authorization 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws RabbitMQPushException {
        p6_Comment p6 = StudWorld.Gson.fromJson(message, p6_Comment.class);
        Comment comment = p6.getComment();
        switch(p6.action) {
            case 0:
            case 1:
                Comment.save(comment);
                break;
            case 2:
                p6.result = Comment.delete(comment);
                break;
        }
        if(!p6.result) {
            p6.self = true;
            StudWorld.RabbitMQ.sendToUser(p6);
        } else {
            StudWorld.RabbitMQ.sendToGroup(p6.getComment().getCommentType(), p6.getComment().getDestinationId(), p6);
        }
    }
    
}
