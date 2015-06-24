 package net.studworld.webclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import net.studworld.system.ClientServer;
import net.studworld.system.StudWorld;
import net.studworld.webclient.logic.AuthLogic;
import net.studworld.webclient.logic.CommentLogic;
import net.studworld.webclient.logic.LikeLogic;
import net.studworld.webclient.logic.LoadUserLogic;
import net.studworld.webclient.logic.PostLogic;
import net.studworld.webclient.logic.PrivateMessageLogic;
import net.studworld.webclient.logic.RegisterLogic;
import net.studworld.webclient.logic.SubscribeLogic;


public class WebsocketServer extends ClientServer {

    /**
     *
     */
    public WebsocketServer() {
        super();
    }

    /**
     *
     * @param client
     */
    public void onDisconnect(Session client) {
        try {
            WebsocketUser u = ServerStorage.getUserBySession(null, client);
            u.removeSession(client);
            if(!u.hasSessions()) {
                u.destroy();
            }
        } catch (IOException ex) {
            Logger.getLogger(WebsocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param client
     */
    public void onConnected(Session client) {
        
    }

    /**
     *
     */
    @Override
    public void beforeStart() {
        this.addLogicMethod(new AuthLogic());
        this.addLogicMethod(new CommentLogic());
        this.addLogicMethod(new LikeLogic());
        this.addLogicMethod(new LoadUserLogic());
        this.addLogicMethod(new PostLogic());
        this.addLogicMethod(new PrivateMessageLogic());
        this.addLogicMethod(new RegisterLogic());
        this.addLogicMethod(new SubscribeLogic());
    }

    /**
     *
     */
    @Override
    public void afterStart() {
        this.setHost(StudWorld.ClientConfig.Server.getHost());
        this.setPort(StudWorld.ClientConfig.Server.getPort());
        this.setId(StudWorld.ClientConfig.Server.getId());
    }

    
}
