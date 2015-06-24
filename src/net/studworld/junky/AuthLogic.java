
package net.studworld.junky;

import net.studworld.models.User;
import net.studworld.packets.p2_AuthUser;
import net.studworld.packets.p3_StatusUser;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class AuthLogic class for adding the logic for package, where id=2;
 * @extends LogicMethod
 * @author Ярослав
 */
public class AuthLogic extends LogicMethod {

    /**
     *
     */
    public AuthLogic(){
        this.setId(2);
    }
    
    /**
     * Execute logic of authorization 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws RabbitMQPushException {
        p2_AuthUser p2 = StudWorld.Gson.fromJson(message, p2_AuthUser.class);
        User u = new User();
        u.setEmail(p2.getEmail());
        u.setPass(p2.getPass());
        p3_StatusUser p3 = new p3_StatusUser();
        p3.hash = p2.hash;
        p3.userHash = p2.userHash;
        if((u = User.search(User.class, u, false))!=null) {
            p3.setStatus(User.ONLINE);
            p3.setUser(u);
        } else {
            p3.result = false;
        }
        StudWorld.RabbitMQ.sendToUser(p3);
    }
    
}
