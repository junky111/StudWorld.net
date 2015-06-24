
package net.studworld.userqueue.logic;


import java.io.IOException;
import net.studworld.packets.p3_StatusUser;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;
import net.studworld.webclient.ServerStorage;
import net.studworld.webclient.WebsocketUser;

/**
 *  Class StatusLogic class for adding the logic for package, where id=3;
 * @extends LogicMethod
 * @author Ярослав
 */
public class StatusLogic extends LogicMethod {

    /**
     *
     */
    public StatusLogic() {
        this.setId(3);
    }

           /**
     * Execute logic of status like. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws java.io.IOException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        p3_StatusUser p3 = StudWorld.Gson.fromJson(message, p3_StatusUser.class);
        try {
            try {
                WebsocketUser u = ServerStorage.findUserByHash(p3.userHash);
                if(!p3.result) {
                    u.send(p3);
                    return;
                }
                WebsocketUser realUser = ServerStorage.findUserByUserId(p3.getUser()._id);
                realUser.addSessions(u.getSessions());
                u.destroy();
            } catch(IllegalStateException e) {
                //Nothing to do, user have no connections
            }
            WebsocketUser u = ServerStorage.findUserByHash(p3.userHash);
            u.destroyHashQueue();
            u.setUser(p3.getUser());
            u.registerQueue();
            u.send(p3);
        } catch(IllegalStateException e) {
            //Nothing to do, user disconnected
        }
    }
}

