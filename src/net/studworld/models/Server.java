package net.studworld.models;




import java.io.IOException;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.DefaultLogic;
import net.studworld.system.logic.LogicMethod;


/**
 * Abstract class Server to create the abstract model of the server.
 * @extends JSONObject
 * @author Ярослав
 */
public abstract class Server extends JSONObject {
    private String host;
    private short port;
    
    private transient LogicMethod firstMethod = new DefaultLogic();
    
    /**
     * Execute message to the queue.
     * @param sender
     * @param packetId
     * @param message 
     */
    public void executeMessage(Object sender, long packetId, String message) {
        System.out.println("[IN] " + message);
        try {
            this.getFirstMethod().run(sender, packetId, message);
        } catch (RabbitMQPushException ex) {
            //StudWorld.Logger.log(ex);
            System.out.println("rabbitmqpushexc");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("network error");
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public abstract void beforeStart();

    /**
     *
     */
    public abstract void afterStart();
    
    /**
     * DONT CHANGE
     * Calls when server example initialize.
     */
    public Server() {
        StudWorld.onServerStarted(this, false);
    }
    
    /**
     * DONT CHANGE
     * Calls when the client and the back-servers initialize.
     * Tells the system about the server start.
     * @param n
     */
    public Server(boolean n) {
        StudWorld.onServerStarted(this, n);
    }
    
    /**
     *
     * @param method
     */
    public void addLogicMethod(LogicMethod method) {
        this.getFirstMethod().add(method);
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public short getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(short port) {
        this.port = port;
    }

    /**
     * @return the firstMethod
     */
    public LogicMethod getFirstMethod() {
        return firstMethod;
    }

}
