/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.userqueue.logic;

import java.io.IOException;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.logic.LogicMethod;
import net.studworld.webclient.WebsocketUser;

/**
 *  Class DirectSendLogic class for adding the logic for package;
 * @extends LogicMethod
 * @author Ярослав
 */
public class DirectSendLogic extends LogicMethod {

           /**
     * Execute logic of direct send. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        ((WebsocketUser)sender).send(message);
    }
}
