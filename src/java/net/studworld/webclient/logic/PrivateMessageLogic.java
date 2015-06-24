
package net.studworld.webclient.logic;

import java.io.IOException;
import net.studworld.packets.p4_PrivateMessage;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;
import net.studworld.webclient.WebsocketUser;

/**
 *  Class PrivateMessageLogic class for adding the logic for package, where id=4;
 * @extends LogicMethod
 * @author Ярослав
 */
public class PrivateMessageLogic extends LogicMethod {

    /**
     *
     */
    public PrivateMessageLogic() {
        this.setId(4);
    }

           /**
     * Execute logic of pm sending. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        p4_PrivateMessage p4 = StudWorld.Gson.fromJson(message, p4_PrivateMessage.class);
        p4.userId = ((WebsocketUser)sender).getUser()._id;
        StudWorld.RabbitMQ.sendToTasks("pm", p4);
    }

}

