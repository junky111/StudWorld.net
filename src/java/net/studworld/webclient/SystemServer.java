
package net.studworld.webclient;

import java.io.IOException;
import net.studworld.system.ClientServer;
import net.studworld.system.RabbitMQExchangePoint;
import net.studworld.system.RabbitMQQueue;
import net.studworld.system.StudWorld;

/**
 *
 * @author Ярослав
 */
public class SystemServer extends ClientServer {

    /**
     *
     */
    public SystemServer() {
        super();
    }

    /**
     *
     */
    @Override
    public void beforeStart() {
        
    }

    /**
     *
     */
    @Override
    public void afterStart() {
        try {
            RabbitMQExchangePoint frontPoint = StudWorld.RabbitMQ.getExchangePoint("client");
            RabbitMQQueue server = new RabbitMQQueue("server"+ServerStorage.clientServer.getId(), false, false, false, null);
            frontPoint.bindQueue(server, "server."+ServerStorage.clientServer.getId());
            server.startListening(null, this);
            StudWorld.RabbitMQ.addQueue(server);
        } catch (IOException ex) {
            StudWorld.Logger.log(ex);
        }
    }
    
}
