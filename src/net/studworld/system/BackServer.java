package net.studworld.system;

import net.studworld.models.Server;


/**
 * Abstract class BackServer  to create the abstract model of the server.
 * @extends Server
 * @author Ярослав
 */

public abstract class BackServer extends Server  {

    /**
     *
     */
    public BackServer() {
        super();
    }

    /**
     *
     * @param n
     */
    public BackServer(boolean n) {
        super(n);
    }
    
    /*public BackServer() throws IOException {
        this(StudWorld.ServerConfig.port);
    }
    
    public BackServer(int port) throws IOException {
        super();
        this.setSocket(new ServerSocket(port));
    }*/
}
