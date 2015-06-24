
package net.studworld.webclient.logic;

import java.io.IOException;
import net.studworld.models.User;
import net.studworld.packets.p2_AuthUser;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;
import net.studworld.webclient.ServerStorage;

/**
 *  Class AuthLogic class for adding the logic for package, where id=2;
 * @extends LogicMethod
 * @author Ярослав
 */
public class AuthLogic extends LogicMethod {

    /**
     *
     */
    public AuthLogic() {
        this.setId(2);
        ServerStorage.accessRules.register(this.getClass(), User.ROLE_GUEST);
    }
    
       /**
     * Execute logic of authorization. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        StudWorld.RabbitMQ.sendToTasks("auth", message);
    }
}

