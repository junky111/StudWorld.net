
package net.studworld.system;

import com.google.gson.Gson;
import java.io.IOException;
import net.studworld.config.ClientConfig;
import net.studworld.config.Config;
import net.studworld.config.ServerConfig;
import net.studworld.models.Server;

/**
 * Abstract class StudWorld  functional for initialisation all the servers
 * @author Ярослав
 */
public abstract class StudWorld {

    /**
     *
     */
    public static ClientConfig ClientConfig = new ClientConfig();

    /**
     *
     */
    public static ServerConfig ServerConfig = new ServerConfig();

    /**
     *
     */
    public static Config ActualConfig;

    /**
     *
     */
    public static RabbitMQ RabbitMQ;

    /**
     *
     */
    public static final Gson Gson = new Gson();

    /**
     *
     */
    public static final Logger Logger = new Logger();

    /**
     *
     */
    public static boolean DEBUG = true;
    private static boolean isRabbitMQstarted = false;

    /**
     *
     */
    public static MongoDBJDBC MongoDB;
    
    /**
     * Initialise all back servers and RabbitMQ services.
     * @param server
     * @param initRabbitMQ 
     */
    public static void onServerStarted(Server server, boolean initRabbitMQ) {
        server.beforeStart();
        if(!isRabbitMQstarted && initRabbitMQ) {
            isRabbitMQstarted = true;
            StudWorld.RabbitMQ = new RabbitMQ();
            StudWorld.RabbitMQ.init();
        }
        server.afterStart();
    }
    
    /**
     * Read all client confugurations.
     * @throws IOException 
     */
    public static void readClientConfig() throws IOException {
        StudWorld.ClientConfig = StudWorld.ClientConfig.instance();
        StudWorld.ActualConfig = StudWorld.ClientConfig;
    }
    
    /**
     * Read all server configurations.
     * @throws IOException 
     */
    public static void readServerConfig() throws IOException {
        StudWorld.ServerConfig = StudWorld.ServerConfig.instance();
        StudWorld.ActualConfig = StudWorld.ServerConfig;
        StudWorld.MongoDB = new MongoDBJDBC();
    }
}
