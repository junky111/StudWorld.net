
package net.studworld.webclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import javax.websocket.Session;
import net.studworld.packets.p12_RegenerateHash;
import net.studworld.system.StudWorld;

/**
 * Class ServerStorage class for initialisation of necessary servers for the client and and working with user using websockets.
 * @author Ярослав
 */

public class ServerStorage {

    /**
     *
     */
    public static final HashMap<String, WebsocketUser> WebUsers = new HashMap<>();

    /**
     *
     */
    public static WebsocketServer clientServer;

    /**
     *
     */
    public static UserQueueServer userQueueServer;

    /**
     *
     */
    public static SystemServer systemServer;

    /**
     *
     */
    public static AccessRules accessRules = new AccessRules();
    public static boolean isServerStorageInitialized = false,

    /**
     *
     */
    isServerStorageInitializationStarted = false;
    private static Random random = new Random();
    
    /**
     * Initialise Websocket, UserQueue and System servers.
     */
    public static void init() {
        try {
            System.out.println("servers initializing");
            clientServer = new WebsocketServer();
            userQueueServer = new UserQueueServer();
            systemServer = new SystemServer();
            System.out.println("servers initialized");
            ServerStorage.isServerStorageInitialized = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Create WebsocketUser by session 
     * @param session session for the authorised user.
     * @return  WebsocketUser instance by session.
     * @throws IOException 
     */
    public static WebsocketUser internalRegisterUser(Session session) throws IOException {
        String hash = ServerStorage.generateRandomString((short)32);
        while(StudWorld.RabbitMQ.issetQueue("hash"+hash)) {
            System.out.println("hash queue " + hash + " exist");
            hash = ServerStorage.generateRandomString((short)32);
        }
        WebsocketUser u = new WebsocketUser(hash);
        u.registerHashQueue();
        u.addSession(session);
        WebUsers.put(hash, u);
        p12_RegenerateHash p12 = new p12_RegenerateHash();
        p12.setNewHash(hash);
        u.send(p12);
        return u;
    }
    
        /**
     * Get WebsocketUser by session and hash
     * @param hash
     * @param session session for the authorised user.
     * @return  WebsocketUser instance by session and hash.
     * @throws IOException 
     */
    public static WebsocketUser getUserBySession(String hash, Session session) throws IOException{
        Iterator<WebsocketUser> it = WebUsers.values().iterator();
        while(it.hasNext()) {
            WebsocketUser u = it.next();
            if(u.hasSession(session)) {
                return u;
            }
        }
        return null;
    }
    
            /**
     * Find WebsocketUser by  hash
     * @param hash hash for the authorised user.
     * @return  WebsocketUser instance by session and hash.     
     */
    public static WebsocketUser findUserByHash(String hash) throws IllegalStateException{
        if(WebUsers.containsKey(hash)) {
            return WebUsers.get(hash);
        }
        throw new IllegalStateException("WebsocketUser with current hash not found");
    }
    
           /**
     * Find WebsocketUser by  user id
     * @param userId id.
     * @return  WebsocketUser instance by user id.
     */
    public static WebsocketUser findUserByUserId(String userId) throws IllegalStateException{
        Iterator<WebsocketUser> it = WebUsers.values().iterator();
        while(it.hasNext()) {
            WebsocketUser u = it.next();
            if(u.getUser()._id.equals(userId)) {
                return u;
            }
        }
        throw new IllegalStateException("WebsocketUser with current hash not found");
    }
    
               /**
     * Find WebsocketUsers by  group id
     * @param groupId.
     * @return  WebsocketUser list instance by group id.
     */
    public static ArrayList<WebsocketUser> findUsersBySubscribeToGroup(String groupId) {
        ArrayList<WebsocketUser> users = new ArrayList<>();
        
        Iterator<WebsocketUser> it = WebUsers.values().iterator();
        while(it.hasNext()) {
            WebsocketUser u = it.next();
            if(u.isSubscribedToGroup(groupId)) {
                users.add(u);
            }
        }
        return users;
    }

    /**
     *
     * @param userId
     * @return
     */
    public static ArrayList<WebsocketUser> findUsersBySubscribeToUser(String userId) {
        ArrayList<WebsocketUser> users = new ArrayList<>();
        
        Iterator<WebsocketUser> it = WebUsers.values().iterator();
        while(it.hasNext()) {
            WebsocketUser u = it.next();
            if(u.isSubscribedToUser(userId)) {
                users.add(u);
            }
        }
        return users;
    }
    private static String generateRandomString(short length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
