/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.webclient.logic;

import java.io.IOException;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class SubscribeLogic class for adding the logic for package, where id=9;
 * @extends LogicMethod
 * @author Ярослав
 */
public class SubscribeLogic extends LogicMethod {

    /**
     *
     */
    public SubscribeLogic() {
        this.setId(9);
    }

           /**
     * Execute logic of subscribing. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        StudWorld.RabbitMQ.sendToTasks("subscribe", message);
    }
    
}
