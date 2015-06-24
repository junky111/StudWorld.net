
package net.studworld.webclient.logic;

import java.io.IOException;
import net.studworld.models.User;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;
import net.studworld.webclient.ServerStorage;


/**
 *  Class CommentLogic class for adding the logic for package, where id=6;
 * @extends LogicMethod
 * @author Ярослав
 */
public class CommentLogic extends LogicMethod {

    /**
     *
     */
    public CommentLogic() {
        this.setId(6);
        ServerStorage.accessRules.register(this.getClass(), User.ROLE_USER);
        ServerStorage.accessRules.register(this.getClass(), User.ROLE_AUTHOR);
        ServerStorage.accessRules.register(this.getClass(), User.ROLE_MODERATOR);
        ServerStorage.accessRules.register(this.getClass(), User.ROLE_ADMIN);
        ServerStorage.accessRules.register(this.getClass(), User.ROLE_ANGEL);
    }

           /**
     * Execute logic of comments. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        StudWorld.RabbitMQ.sendToTasks("comment", message);
    }
}
