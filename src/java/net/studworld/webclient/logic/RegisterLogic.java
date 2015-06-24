
package net.studworld.webclient.logic;

import java.io.IOException;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;





/**
 *  Class RegisterLogic class for adding the logic for package, where id=4;
 * @extends LogicMethod
 * @author Ярослав
 */
public class RegisterLogic extends LogicMethod {

    /**
     *
     */
    public RegisterLogic() {
        this.setId(1);
    }

           /**
     * Execute logic of registration. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        StudWorld.RabbitMQ.sendToTasks("register", message);
    }
    
}

