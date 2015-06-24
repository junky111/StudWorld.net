package net.studworld.packets;

import net.studworld.models.Subscribe;

/**
 * Class p9_Subscribe  with necessary fields.Data packet for transactions with id = 9.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p9_Subscribe extends Packet {
    private Subscribe subscribe;

    /**
     *
     */
    public p9_Subscribe() {
        this.setId(9);
    }

    /**
     *
     * @return
     */
    public Subscribe getSubscribe() {
        return subscribe;
    }

    /**
     *
     * @param subscribe
     */
    public void setSubscribe(Subscribe subscribe) {
        this.subscribe = subscribe;
    }
}
