package net.studworld.packets;

import net.studworld.models.User;

/**
 * Class p8_User  with necessary fields.Data packet for transactions with id = 8.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p8_User extends Packet {
    private User user;

    /**
     *
     */
    public p8_User() {
        this.setId(8);
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
