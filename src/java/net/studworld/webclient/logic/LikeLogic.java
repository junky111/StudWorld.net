
package net.studworld.webclient.logic;

import java.io.IOException;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class LikeLogic class for adding the logic for package, where id=7;
 * @extends LogicMethod
 * @author Ярослав
 */
public class LikeLogic extends LogicMethod{

    /**
     *
     */
    public LikeLogic() {
        this.setId(7);
    }

           /**
     * Execute logic of liking. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        StudWorld.RabbitMQ.sendToTasks("like", message);
    }
}
