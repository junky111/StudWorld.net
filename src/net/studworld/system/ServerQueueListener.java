
package net.studworld.system;


import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.studworld.models.Server;
import net.studworld.packets.Packet;

/**
 * Class ServerQueueListener  functional listening to RabbitMQ queues.
 * @author Ярослав
 */
public class ServerQueueListener implements Runnable {
    private String queueName;
    private QueueingConsumer consumer;
    private Server server;
    private Object sender;

    /**
     *
     * @param queueName
     * @param sender
     * @param server
     * @param consumer
     */
    public ServerQueueListener(String queueName, Object sender, Server server, QueueingConsumer consumer) {
        this.server = server;
        this.consumer = consumer;
        this.sender = sender;
        this.queueName = queueName;
    }
    /**
     * Run the ServerQueue listener.
     */
    @Override
    public void run() {
        System.out.println("listening to queue: " + queueName);
        while(true) {
            try {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                Packet p = Packet.fromJson(message, Packet.class);
                if(StudWorld.DEBUG) {
                    System.out.println("[IN "+this.server.getClass().getSimpleName()+"] "+message);
                }
                this.server.executeMessage(sender, p.getId(),  message);
            } catch (InterruptedException | ConsumerCancelledException ex) {
                System.out.println("disconnected from queue: " + queueName);
                //StudWorld.Logger.log(ex);
                ex.printStackTrace();
                Logger.getLogger(ServerQueueListener.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
    
}
