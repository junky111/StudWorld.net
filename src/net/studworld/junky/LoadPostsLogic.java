/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import com.mongodb.DBCursor;
import java.util.ArrayList;
import net.studworld.models.Comment;
import net.studworld.models.Post;
import net.studworld.models.PrivateMessage;
import net.studworld.packets.p11_PrivateMessagesList;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;


/**
 *  Class LoadPostsLogic class for adding the logic for package, where id=20;
 * @extends LogicMethod
 * @author Ярослав
 */
public class LoadPostsLogic extends LogicMethod {
    private int packageSize = 20;

    /**
     *
     */
    public LoadPostsLogic(){
        this.setId(11);
    }
    
       /**
     * Execute logic of loading posts 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws RabbitMQPushException {
        ArrayList<PrivateMessage> messages = new ArrayList<>();
        p11_PrivateMessagesList p11 = StudWorld.Gson.fromJson(message, p11_PrivateMessagesList.class);
        Post p = new Post();
        p.setUserId(p11.getFromUserId());
        DBCursor cursor = StudWorld.MongoDB.showDocuments(p);
        cursor.skip((int) (p11.getOffset()*packageSize));
        for(int i = 0; i < this.packageSize; i++) {
            if(!cursor.iterator().hasNext()) break;
            messages.add((PrivateMessage) cursor.iterator().next());
        }
        p11.setPosts(messages.toArray(new PrivateMessage[messages.size()]));
        StudWorld.RabbitMQ.sendToUser(p11);
    }
}

