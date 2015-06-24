/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.packets;

import net.studworld.models.Comment;

/**
 * Class p6_Comment  with necessary fields.Data packet for transactions with id = 6.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p6_Comment extends Packet {
    private Comment comment;
    
    /**
     *
     */
    public p6_Comment() {
        this.setId(6);
    }

    /**
     *
     * @return
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }

   

   
}
