package net.studworld.packets;

import net.studworld.system.ClientServer;

/**
 * Class p10_Reconnect  with necessary fields.Data packet for transactions with id = 10.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p10_Reconnect extends Packet {
    
    private ClientServer server;

    /**
     *
     */
    public p10_Reconnect() {
        this.setId(10);
    }

    /**
     * @return the server
     */
    public ClientServer getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(ClientServer server) {
        this.server = server;
    }
}
