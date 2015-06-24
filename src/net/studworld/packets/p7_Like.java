
package net.studworld.packets;

import net.studworld.models.Like;

/**
 * Class p7_Like  with necessary fields.Data packet for transactions with id = 7.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p7_Like extends Packet {
    private Like like;
    private String objectLiked;
    private int itemType;

    /**
     *
     */
    public p7_Like() {
        this.setId(7);
    }

    /**
     *
     * @return
     */
    public Like getLike() {
        return like;
    }

    /**
     *
     * @param like
     */
    public void setLike(Like like) {
        this.like = like;
    }

    /**
     * @return the objectLiked
     */
    public String getObjectLiked() {
        return objectLiked;
    }

    /**
     * @param objectLiked the objectLiked to set
     */
    public void setObjectLiked(String objectLiked) {
        this.objectLiked = objectLiked;
    }

    /**
     * @return the itemType
     */
    public int getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

}
