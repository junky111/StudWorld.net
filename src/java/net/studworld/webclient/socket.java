
package net.studworld.webclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import net.studworld.models.JSONObject;
import net.studworld.packets.Packet;

/**
 *
 * @author Ярослав
 */
@ServerEndpoint("/socket")
public class socket {
    
    /**
     *
     * @param exception
     * @param session
     * @throws IOException
     */
    @OnError
    public void OnError(Throwable exception, Session session) throws IOException {
    }

    /**
     *
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        if(!ServerStorage.isServerStorageInitialized) {
            session.close();
            return;
        }
        ServerStorage.internalRegisterUser(session);
    }
 
    /**
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session){
        try {
            if(ServerStorage.isServerStorageInitialized==false) return;
            Packet p = JSONObject.fromJson(message, Packet.class);
            WebsocketUser u = ServerStorage.getUserBySession(p.userHash, session);
            ServerStorage.clientServer.executeMessage(u, p.getId(), message);
        } catch (IOException ex) {
            Logger.getLogger(socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    /**
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        if(ServerStorage.isServerStorageInitialized) {
            ServerStorage.clientServer.onDisconnect(session);
        }
    }
    
}
