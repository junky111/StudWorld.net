/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.main;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.studworld.junky.AuthLogic;
import net.studworld.junky.CommentPostLogic;
import net.studworld.junky.LoadUserLogic;
import net.studworld.junky.PostPostLogic;
import net.studworld.junky.PrivateMessageLogic;
import net.studworld.junky.RegisterLogic;
import net.studworld.junky.StatusLikeLogic;
import net.studworld.system.BackServer;
import net.studworld.system.StudWorld;

/**
 *
 * @author Ярослав
 */
public class JunkyServer extends BackServer {

    /**
     *
     */
    public JunkyServer() {
        super(true);
    }
    @Override
    public void beforeStart() {
        this.addLogicMethod(new RegisterLogic());
        this.addLogicMethod(new AuthLogic());
        this.addLogicMethod(new PrivateMessageLogic());
        this.addLogicMethod(new PostPostLogic());
        this.addLogicMethod(new CommentPostLogic());
        this.addLogicMethod(new StatusLikeLogic());
        this.addLogicMethod(new LoadUserLogic());
        
        try {
            StudWorld.readServerConfig();
        } catch (IOException ex) {
            StudWorld.Logger.log(ex);
        }
    }

    @Override
    public void afterStart() {
        try {
            StudWorld.RabbitMQ.getQueue("tasks").startListening(null, this);
        } catch (ShutdownSignalException | ConsumerCancelledException | IOException ex) {
            Logger.getLogger(JunkyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
