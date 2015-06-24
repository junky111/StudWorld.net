package net.studworld.webclient;

import java.io.IOException;
import net.studworld.packets.Packet;
import net.studworld.system.ClientServer;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.userqueue.logic.CommentLogic;
import net.studworld.userqueue.logic.DefaultLogic;
import net.studworld.userqueue.logic.LikeLogic;
import net.studworld.userqueue.logic.LoadUserLogic;
import net.studworld.userqueue.logic.PostLogic;
import net.studworld.userqueue.logic.PrivateMessageLogic;
import net.studworld.userqueue.logic.StatusLogic;

/**
 *
 * @author Ярослав
 */
public class UserQueueServer extends ClientServer {

    /**
     *
     * @throws IOException
     */
    public UserQueueServer() throws IOException {
        super(true);
    }

    /**
     *
     * @param sender
     * @param packetId
     * @param message
     */
    @Override
    public void executeMessage(Object sender, long packetId, String message) {
        try {
            Packet p = StudWorld.Gson.fromJson(message, Packet.class);
            this.getFirstMethod().run(ServerStorage.findUserByUserId(p.userId), packetId, message);
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
    @Override
    public void beforeStart() {
        try {
            this.addLogicMethod(new DefaultLogic());
            this.addLogicMethod(new StatusLogic());
            this.addLogicMethod(new PrivateMessageLogic());
            this.addLogicMethod(new CommentLogic());
            this.addLogicMethod(new LikeLogic());
            this.addLogicMethod(new LoadUserLogic());
            this.addLogicMethod(new PostLogic());
            StudWorld.readClientConfig();
        } catch (IOException ex) {
            StudWorld.Logger.log(ex);
        }
    }

    /**
     *
     */
    @Override
    public void afterStart() {
    }
    
}
