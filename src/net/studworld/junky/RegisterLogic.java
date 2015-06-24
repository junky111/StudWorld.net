/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.junky;

import net.studworld.models.User;
import net.studworld.packets.p1_RegisterUser;
import net.studworld.packets.p3_StatusUser;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;


/**
 *  Class RegisterLogic class for adding the logic for package, where id=8;
 * @extends LogicMethod
 * @author Ярослав
 */
public class RegisterLogic extends LogicMethod{
    
    /**
     *
     */
    public RegisterLogic(){
        this.setId(1);
    }

       /**
     * Execute logic of registration 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws RabbitMQPushException {
        p1_RegisterUser p1 = StudWorld.Gson.fromJson(message, p1_RegisterUser.class);
        p3_StatusUser p3 = new p3_StatusUser();
        User u = new User();
        u.setEmail(p1.getEmail());
        u = User.search(User.class, u, false);
        if(u!=null) {
            p3.result=false;
            StudWorld.RabbitMQ.sendToUser(p3);
        } else {
            u = new User();
            u.setAvatar("//pp.vk.me/c621829/v621829267/1802c/gUCs-kXoY34.jpg");
            u.setBackground("//unsplash.imgix.net/reserve/MTuhqSiPQbeVnaIYU16X_P1160798_adj.jpg?q=75&fm=jpg&s=ede6dca4fff55fa0cc6ec27bcd3480d9");
            u.setName(p1.getName());
            u.setSurname(p1.getSurname());
            u.setPhoneNumber(p1.getPhoneNumber());
            u.setPass(p1.getPass());
            u.setFriendsCount("0");
            u.setEmail(p1.getEmail());
            User.create(u);
            
            p3.hash = p1.hash;
            p3.userHash = p1.userHash;
            p3.setStatus(User.ONLINE);
            p3.setUser(u);
        }
        StudWorld.RabbitMQ.sendToUser(p3);
    }
}
