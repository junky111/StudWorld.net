package net.studworld.system;



import net.studworld.models.Server;

/**
 * Abstract class ClientServer  to create the abstract model of the server.
 * @extends Server
 * @author Ярослав
 */
public abstract class ClientServer extends Server {

    /**
     *
     */
    public ClientServer() {
        super();
    }

    /**
     *
     * @param n
     */
    public ClientServer(boolean n) {
        super(n);
    }
    /*public ClientServer() throws IOException {
        this(StudWorld.ClientConfig.port);
    }
    
    public ClientServer(int port) throws IOException {
        super();
        this.setSocket(new ServerSocket(port));
    }*/
}
