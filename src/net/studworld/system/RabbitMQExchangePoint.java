

package net.studworld.system;

import com.rabbitmq.client.Channel;
import java.io.IOException;

/**
 * Class RabbitMQExchangePoint  functional for working with RabbitMQ exchange points.
 * @author Ярослав
 */
public class RabbitMQExchangePoint {
    private String name, type;
    private Channel channel;

    /**
     *
     * @param exchangeName
     * @param exchangeType
     * @throws IOException
     */
    public RabbitMQExchangePoint(String exchangeName, String exchangeType) throws IOException{
        this.channel = StudWorld.RabbitMQ.getConnection().createChannel();
        this.name = exchangeName;
        this.type = exchangeType;
        this.channel.exchangeDeclare(exchangeName, exchangeType);
    }
    
    /**
     * Send message by routing key.
     * @param routingKey
     * @param message
     * @throws RabbitMQPushException 
     */
    public void pushMessage(String routingKey, String message) throws RabbitMQPushException {
        try {
            this.channel.basicPublish(this.getName(), routingKey, null, message.getBytes());
        } catch (IOException ex) {
            throw (RabbitMQPushException)ex;
        }
    }
    
    /**
     * Bind queue to the exchange point by the routing key
     * @param queue
     * @param routingKey
     * @throws IOException 
     */
    public void bindQueue(RabbitMQQueue queue, String routingKey) throws IOException {
        this.channel.queueBind(queue.getName(), this.getName(), routingKey);
    }
  /**
     *Unbind queue from the exchange point by the routing key
     * @param queue
     * @param routingKey
     * @throws IOException 
     */
    public void unbindQueue(RabbitMQQueue queue, String routingKey) throws IOException {
        this.channel.queueUnbind(queue.getName(), this.getName(), routingKey);
    }
    /**
     *  Remove queue by its instance.
     * @param queue
     * @throws IOException 
     */
    public void removeQueue(RabbitMQQueue queue) throws IOException {
        this.channel.queueDelete(queue.getName());
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
}