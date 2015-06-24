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
 *  Class PostLogic class for adding the logic for package, where id=5;
 * @extends LogicMethod
 * @author Ярослав
 */
public class PostLogic extends LogicMethod {

    /**
     *
     */
    public PostLogic() {
        this.setId(5);
    }

           /**
     * Execute logic of post adding. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        //StudWorld.RabbitMQ.process(new RabbitMQMessage("back", "task.post", message));
        StudWorld.RabbitMQ.sendToTasks("post", message);
    }

}
