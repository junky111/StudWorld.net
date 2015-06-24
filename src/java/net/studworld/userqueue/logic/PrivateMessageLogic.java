
package net.studworld.userqueue.logic;


import java.io.IOException;
import net.studworld.packets.p4_PrivateMessage;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;
import net.studworld.webclient.ServerStorage;
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
     * Execute logic of pm sending 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        p4_PrivateMessage p4 = StudWorld.Gson.fromJson(message, p4_PrivateMessage.class);
        try {
            WebsocketUser u;
            if(p4.self) {
                u = ServerStorage.findUserByUserId(p4.userId);
            } else {
                u = ServerStorage.findUserByUserId(p4.getMessage().getToUserId());
            }
            u.send(message);
        } catch(IllegalStateException e) {
            
        }
    }

}

