
package net.studworld.packets;

import java.util.ArrayList;
import net.studworld.models.Subscribe;

/**
 * Class p13_SubscribeList  with necessary fields.Data packet for transactions with id = 13.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p13_SubscribeList extends Packet{
          
    private long subId;
    private ArrayList<Subscribe> sub;
    
    /**
     *
     */
    public p13_SubscribeList() {
        this.setId(13);
    }

    /**
     * @return the subId
     */
    public long getSubId() {
        return subId;
    }

    /**
     * @param subId the subId to set
     */
    public void setSubId(long subId) {
        this.subId = subId;
    }

    /**
     * @return the sub
     */
    public ArrayList<Subscribe> getSub() {
        return sub;
    }

    /**
     * @param sub the sub to set
     */
    public void setSub(ArrayList<Subscribe> sub) {
        this.sub = sub;
    }

  
}
