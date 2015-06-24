
package net.studworld.system;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;
import net.studworld.models.Server;

/**
 * Class RabbitMQQueue  functional for working with RabbitMQ queues.
 * @author Ярослав
 */
public class RabbitMQQueue {
    private String name;
    private Channel channel;

    /**
     *
     * @param queueName
     * @param durable
     * @param exclusive
     * @param nowait
     * @param arguments
     * @throws IOException
     */
    public RabbitMQQueue(String queueName,boolean durable, boolean exclusive, boolean nowait, java.util.Map<String,Object> arguments) throws IOException{
        this.name = queueName;
        this.channel = StudWorld.RabbitMQ.getConnection().createChannel();
        this.channel.queueDeclare(queueName,durable,exclusive,nowait, arguments);
    }

    /**
     * Start listening to the server by sender.
     * @param sender
     * @param server
     * @throws IOException 
     */
    public void startListening(Object sender, Server server) throws IOException {
        for(int i = 0; i < StudWorld.ActualConfig.queueWorkerNum; i++) {
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(this.name, true, consumer);
            ServerQueueListener listener = new ServerQueueListener(this.name, sender, server, consumer);
            new Thread(listener).start();
        }
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
