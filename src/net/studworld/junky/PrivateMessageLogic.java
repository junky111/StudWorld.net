package net.studworld.junky;

import com.mongodb.BasicDBObject;
import net.studworld.models.PrivateMessage;
import net.studworld.models.User;
import net.studworld.packets.p4_PrivateMessage;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class PrivateMessageLogic class for adding the logic for package, where id=8;
 * @extends LogicMethod
 * @author Ярослав
 */
public class PrivateMessageLogic extends LogicMethod{
    
    /**
     *
     */
    public PrivateMessageLogic(){
        this.setId(4);
    }
    
       /**
     * Execute logic of private messages sending 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws RabbitMQPushException {
        p4_PrivateMessage p4 = StudWorld.Gson.fromJson(message, p4_PrivateMessage.class);
        
        
        BasicDBObject query = new BasicDBObject();
        query.put("id", p4.userId);
        User u = new User();
        u._id = p4.userId;
        u = User.search(User.class, u, true);
        if(u!=null) {
            PrivateMessage.save(p4.getMessage());
            //p4.getMessage().setName(u.getName() + ' ' + u.getSurname());
            p4.self = true;
            StudWorld.RabbitMQ.sendToUser(p4); //подтверждение

            p4.self = false;
            p4.userId = p4.getMessage().getToUserId();
            StudWorld.RabbitMQ.sendToUser(p4); //реальный получатель
        }
    }
    
}
