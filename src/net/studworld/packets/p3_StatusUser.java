
package net.studworld.packets;

import net.studworld.models.User;

/**
 * Class p3_StatusUser  with necessary fields.Data packet for transactions with id = 3.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p3_StatusUser extends Packet {
    /**
     * Если у нас будут ещё статусы, то нужно этот момент предусмотреть
     * 0 - offline
     * 1 - online
     * 2 - updated
     */
    private short status = 0;
    private User user;

    /**
     *
     */
    public p3_StatusUser() {
        this.setId(3);
    }

    /**
     * @return the status
     */
    public short getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(short status) {
        this.status = status;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }


}
