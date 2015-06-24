
package net.studworld.packets;

import net.studworld.models.PrivateMessage;

/**
 * Class p4_PrivateMessage  with necessary fields.Data packet for transactions with id = 4.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p4_PrivateMessage extends Packet {
    private PrivateMessage message;

    /**
     *
     */
    public p4_PrivateMessage() {
        this.setId(4);
    }

    /**
     * @return the message
     */
    public PrivateMessage getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(PrivateMessage message) {
        this.message = message;
    }
}
