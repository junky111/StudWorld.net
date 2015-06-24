/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.packets;

import java.util.ArrayList;
import net.studworld.models.Comment;

/**
 * Class p16_CommentsList  with necessary fields.Data packet for transactions with id = 16.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p16_CommentsList extends Packet {
    private ArrayList<Comment> comments = new ArrayList<>();
    transient private Comment query;
    private long offset;

    /**
     *
     */
    public p16_CommentsList() {
        this.setId(16);
    }

    /**
     * @return the comments
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @return the query
     */
    public Comment getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(Comment query) {
        this.query = query;
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
    
}
