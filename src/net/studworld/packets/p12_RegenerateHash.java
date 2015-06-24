
package net.studworld.packets;

/**
 * Class p12_ReganerateHash  with necessary fields.Data packet for transactions with id = 12.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */

public class p12_RegenerateHash extends Packet {
    private String newHash = "";

    /**
     *
     */
    public p12_RegenerateHash() {
        this.setId(12);
    }

    /**
     * @return the newHash
     */
    public String getNewHash() {
        return newHash;
    }

    /**
     * @param newHash the newHash to set
     */
    public void setNewHash(String newHash) {
        this.newHash = newHash;
    }
}
