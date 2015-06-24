/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import java.io.IOException;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.logic.LogicMethod;

/**
 *  Class SubscribeListLogic class for adding the logic for package, where id=8;
 * @extends LogicMethod
 * @author Ярослав
 */
public class SubscribeListLogic extends LogicMethod{
    
    /**
     *
     */
    public SubscribeListLogic(){
        this.setId(13);
    }
    
       /**
     * Execute logic of subscribe list creating  
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws IOException, RabbitMQPushException {
        
        /*p13_SubscribeList p16 = StudWorld.Gson.fromJson(message, p16_LoadComments.class);
        DBCursor cursor = StudWorld.MongoDB.showDocuments(p16.getQuery());
        cursor.skip((int) (p16.getOffset()*packageSize));
        for(int i = 0; i < this.packageSize; i++) {
            if(!cursor.iterator().hasNext()) break;
            p16.getComments().add((Comment) cursor.iterator().next());
        }
        StudWorld.RabbitMQ.sendToUser(p16);*/
    }
    
}
