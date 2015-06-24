package net.studworld.webclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.websocket.Session;
import net.studworld.models.Subscribe;
import net.studworld.models.User;
import net.studworld.packets.Packet;
import net.studworld.system.RabbitMQExchangePoint;
import net.studworld.system.RabbitMQQueue;
import net.studworld.system.StudWorld;

/**
 *
 * @author Ярослав
 */
public class WebsocketUser {
    private ArrayList<Session> sessions = new ArrayList<>();
    private String userHash;
    private RabbitMQQueue queue;
    private User user = new User();

    /**
     *
     * @param hash
     */
    public WebsocketUser(String hash) {
        this.userHash = hash;
    }

    /**
     *
     * @param message
     * @throws IOException
     */
    public void send(String message) throws IOException {
        if(StudWorld.DEBUG) {
            System.out.println("[OUT Websocket] " + message);
        }
        for (Iterator<Session> it = sessions.iterator(); it.hasNext();) {
            it.next().getBasicRemote().sendText(message);
        }
    }

    /**
     *
     * @param p
     * @throws IOException
     */
    public void send(Packet p) throws IOException {
        this.send(p.toJson());
    }

    /**
     *
     * @param s
     */
    public void addSession(Session s) {
        this.sessions.add(s);
    }

    /**
     *
     * @param s
     */
    public void addSessions(Collection<Session> s) {
        this.sessions.addAll(s);
    }

    /**
     *
     * @return
     */
    public ArrayList<Session> getSessions() {
        return this.sessions;
    }

    /**
     *
     * @param s
     */
    public void removeSession(Session s) {
        this.sessions.remove(s);
    }

    /**
     *
     * @param session
     * @return
     */
    public boolean hasSession(Session session) {
        return this.sessions.contains(session);
    }

    /**
     *
     * @return
     */
    public boolean hasSessions() {
        return !this.sessions.isEmpty();
    }

    /**
     *
     */
    public void destroy() {
        ServerStorage.WebUsers.remove(this.userHash);
        this.destroyHashQueue();
        this.destroyUserQueue();
    }

    /**
     *
     */
    public void destroyHashQueue() {
        try {
            if(this.getUser()==null) {
                RabbitMQExchangePoint users = StudWorld.RabbitMQ.getExchangePoint("users");
                RabbitMQQueue localQueue = StudWorld.RabbitMQ.getQueue("hash"+this.userHash);
                users.removeQueue(localQueue);
            }
        } catch (IOException ex) {
            StudWorld.Logger.log(ex);
        }
    }

    /**
     *
     */
    public void destroyUserQueue() {
        try {
            if(this.getUser()!=null) {
                RabbitMQExchangePoint users = StudWorld.RabbitMQ.getExchangePoint("users");
                RabbitMQQueue localQueue = StudWorld.RabbitMQ.getQueue("user"+this.getUser()._id);
                users.removeQueue(localQueue);
            }
        } catch (IOException ex) {
            StudWorld.Logger.log(ex);
        }
    }

    /**
     * @return the userHash
     */
    public String getUserHash() {
        return userHash;
    }

    /**
     * @param userHash the userHash to set
     */
    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    /**
     *
     * @throws IOException
     */
    public void registerQueue() throws IOException {
        
        RabbitMQExchangePoint users = StudWorld.RabbitMQ.getExchangePoint("users");
        users.removeQueue(this.queue);
        this.queue = new RabbitMQQueue("user"+this.user._id, false, true, false, null);
        users.bindQueue(this.queue, "user."+this.user._id);
        try {
            this.queue.startListening(this, ServerStorage.userQueueServer);
            StudWorld.RabbitMQ.addQueue(this.queue);
        } catch (IOException ex) {
            ex.printStackTrace();
            /*p10_ReconnectUser p10 = new p10_ReconnectUser();
            p10.setServer(ServerStorage.clientServer);
            ((Session)sender).getBasicRemote().sendText(p10.toJson());*/
        }
    }

    /**
     *
     * @throws IOException
     */
    public void registerHashQueue() throws IOException {
        RabbitMQExchangePoint users = StudWorld.RabbitMQ.getExchangePoint("users");
        this.queue = new RabbitMQQueue("hash"+this.userHash, false, false, false, null);
        users.bindQueue(this.queue, "hash."+this.userHash);
        try {
            this.queue.startListening(this, ServerStorage.userQueueServer);
            StudWorld.RabbitMQ.addQueue(this.queue);
        } catch (IOException ex) {
            ex.printStackTrace();
            /*p10_ReconnectUser p10 = new p10_ReconnectUser();
            p10.setServer(ServerStorage.clientServer);
            ((Session)sender).getBasicRemote().sendText(p10.toJson());*/
        }
    }

    /**
     * @return the queue
     */
    public RabbitMQQueue getQueue() {
        return queue;
    }


    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @param groupId
     * @return
     */
    public boolean isSubscribedToGroup(String groupId) {
        if(this.user!=null) {
            Iterator<Subscribe> it = this.user.getSubscribes().iterator();
            while(it.hasNext()) {
                if(it.next().getToGroupId().equals(groupId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param userId
     * @return
     */
    public boolean isSubscribedToUser(String userId) {
        if(this.user!=null) {
            Iterator<Subscribe> it = this.user.getSubscribes().iterator();
            while(it.hasNext()) {
                if(it.next().getToUserId().equals(userId)) {
                    return true;
                }
            }
        }
        return false;
    }
}
