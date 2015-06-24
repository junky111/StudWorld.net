/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import java.io.IOException;
import net.studworld.models.Subscribe;
import net.studworld.packets.p9_Subscribe;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class SubscribeLogic class for adding the logic for package, where id=8;
 * @extends LogicMethod
 * @author Ярослав
 */
public class SubscribeLogic extends LogicMethod {
    
    /**
     *
     */
    public SubscribeLogic(){
        this.setId(9);
    }
    
       /**
     * Execute logic of subscribing 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        
        p9_Subscribe p9 = StudWorld.Gson.fromJson(message, p9_Subscribe.class);
        Subscribe news = new Subscribe();
        news.setToGroupId(p9.getSubscribe().getToGroupId());
        news.setToUserId(p9.getSubscribe().getToUserId());
        news.setUserId(p9.getSubscribe().getUserId());
        Subscribe s = Subscribe.search(Subscribe.class, news, false);
        if(p9.getSubscribe().getState()==0) {
            Subscribe.delete(s);
        } else {
            Subscribe.save(s);
        }
        p9.self = true;
        StudWorld.RabbitMQ.sendToUser(p9);
        if(!s.getToUserId().isEmpty()) {
            p9.self = false;
            p9.userId = p9.getSubscribe().getToUserId();
            StudWorld.RabbitMQ.sendToUser(p9);
        }
    } 
}
