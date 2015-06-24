/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.packets;

import net.studworld.models.Post;
import net.studworld.models.PrivateMessage;

/**
 * Class p11_PrivateMessagesList  with necessary fields.Data packet for transactions with id = 11.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p11_PrivateMessagesList extends Packet {
    private String fromUserId, fromHistoryId;
    private long offset;
    private PrivateMessage[] pms = new PrivateMessage[0];

    /**
     *
     */
    public p11_PrivateMessagesList() {
        this.setId(11);
    }

    /**
     * @return the fromUserId
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * @param fromUserId the fromUserId to set
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * @return the fromHistoryId
     */
    public String getFromHistoryId() {
        return fromHistoryId;
    }

    /**
     * @param fromHistoryId the fromHistoryId to set
     */
    public void setFromHistoryId(String fromHistoryId) {
        this.fromHistoryId = fromHistoryId;
    }

    /**
     * @return the offset
     */
    public long getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(long offset) {
        this.offset = offset;
    }

    /**
     * @return the pms
     */
    public PrivateMessage[] getPMs() {
        return pms;
    }

    /**
     * @param pms
     */
    public void setPosts(PrivateMessage[] pms) {
        this.pms = pms;
    }
}
