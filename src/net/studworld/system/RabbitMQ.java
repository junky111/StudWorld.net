package net.studworld.system;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.studworld.packets.Packet;

/**
 * Class RabbitMQ  functional for working with RabbitMQ.
 * @author Ярослав
 */
public class RabbitMQ {
    private List<RabbitMQQueue> list = new ArrayList<>();
    private List<RabbitMQExchangePoint> points = new ArrayList<>();
    private ConnectionFactory factory;
    private Connection connection;
    
    /**
     *
     */
    public RabbitMQ() {
        try {
            this.factory = new ConnectionFactory();
            factory.setHost(StudWorld.ActualConfig.rabbitMQData.host);
            this.connection = factory.newConnection();
        } catch (IOException | ShutdownSignalException | ConsumerCancelledException ex) {
            StudWorld.Logger.log(ex);
            
        }
    }
    
    /**
     * Send packet to the group by type and id
     * @param itemType
     * @param itemId
     * @param message
     * @throws RabbitMQPushException 
     */
    public void sendToGroup(int itemType, String itemId, Packet message) throws RabbitMQPushException {
        this.pushMessage("groups", "group."+itemType+"."+itemId, message.toJson());
    }
    
        /**
     * Send packet to user
     * @param p the instance of Packet 
     * @throws RabbitMQPushException 
     */
    public void sendToUser(Packet p) throws RabbitMQPushException {
        this.pushMessage("users", p.logged ? "user."+p.userId : "hash."+p.userHash, p.toJson());
    }
            /**
     * Send packet to tasks
     * @param p the instance of Packet
     * @param type type of the task
     * @throws RabbitMQPushException 
     */
    public void sendToTasks(String type, Packet p) throws RabbitMQPushException {
        this.pushMessage("back", "task."+type, p.toJson());
    }
    
                /**
     * Send message to tasks type
     * @param message
     * @param type type of the task
     * @throws RabbitMQPushException 
     */
    public void sendToTasks(String type, String message) throws RabbitMQPushException {
        this.pushMessage("back", "task."+type, message);
    }
    
    /**
     * Send message to the exchange point by the routingKey
     * @param exchangePoint
     * @param rountingKey
     * @param message
     * @throws RabbitMQPushException 
     */
    public void pushMessage(String exchangePoint, String rountingKey, String message) throws RabbitMQPushException {
        Iterator<RabbitMQExchangePoint> it = this.points.iterator();
        RabbitMQExchangePoint point = it.next();
        while(!point.getName().equalsIgnoreCase(exchangePoint)) {
            point = it.next();
        }
        if(StudWorld.DEBUG) {
            System.out.println();
            System.out.println("[OUT TO "+exchangePoint+" BY KEY " + rountingKey+"]");
            System.out.println(message);
        }
        point.pushMessage(rountingKey, message);
    }
    
    
    /**
     * initialisation of all RabbitMQ system.
     */
    public void init() {
        try {
            /* BACK NEEDS */
            RabbitMQExchangePoint backPoint = new RabbitMQExchangePoint("back", "topic");
            RabbitMQExchangePoint frontPoint = new RabbitMQExchangePoint("client", "topic");
            
            RabbitMQQueue tasks = new RabbitMQQueue("tasks", true, false, false, null);
            RabbitMQQueue load = new RabbitMQQueue("load", false, false, false, null);
            RabbitMQQueue log = new RabbitMQQueue("log", true, false, false, null);
            RabbitMQQueue error = new RabbitMQQueue("error", true, false, false, null);
            
            backPoint.bindQueue(tasks, "task.#");
            backPoint.bindQueue(log, "log.log");
            backPoint.bindQueue(error, "log.error");
            backPoint.bindQueue(load, "load.#");
            
            /* FRONT NEEDS */
            RabbitMQExchangePoint users = new RabbitMQExchangePoint("users", "topic");
            RabbitMQExchangePoint groups = new RabbitMQExchangePoint("groups", "topic");
            
            this.addExchangePoint(backPoint);
            this.addExchangePoint(frontPoint);
            this.addExchangePoint(users);
            this.addExchangePoint(groups);
            
            this.addQueue(tasks);
            this.addQueue(log);
            this.addQueue(error);
            this.addQueue(load);
        } catch (IOException ex) {
            StudWorld.Logger.log(ex);
        }
    }
    
    /**
     *
     * @param queue
     */
    public void addQueue(RabbitMQQueue queue) {
        this.list.add(queue);
    }
    
    /**
     *
     * @param point
     */
    public void addExchangePoint(RabbitMQExchangePoint point) {
        this.points.add(point);
    }
    
    /**
     *
     * @param name
     * @return
     */
    public RabbitMQExchangePoint getExchangePoint(String name) {
        for(RabbitMQExchangePoint point : this.points) {
            if(point.getName().equalsIgnoreCase(name)) {
                return point;
            }
        }
        return null;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public RabbitMQQueue getQueue(String name) {
        for(RabbitMQQueue queue : this.list) {
            if(queue.getName().equalsIgnoreCase(name)) {
                return queue;
            }
        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean issetQueue(String name) {
        try {
            this.connection.createChannel().queueDeclarePassive(name);
            return true;
        } catch(IOException e) {
            return false;
        }
    }
    /**
     * @return the channel
     */
    public Connection getConnection() {
        return connection;
    }
}
