/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.packets;

import java.util.ArrayList;

/**
 * Class p17_SubscribsList  with necessary fields.Data packet for transactions with id = 17.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p17_SubscribsList extends Packet {
    private ArrayList<String> subIds= new ArrayList<>();
    private String id; 

    /**
     *
     */
    public p17_SubscribsList(){
        this.setId(17);
    }

    /**
     * @return the subIds
     */
    public ArrayList<String> getSubIds() {
        return subIds;
    }

    /**
     * @param subIds the subIds to set
     */
    public void setSubIds(ArrayList<String> subIds) {
        this.subIds = subIds;
    }

    /**
     * @return the id
     */
    public String getUserId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setUserId(String id) {
        this.id = id;
    }
    
}
