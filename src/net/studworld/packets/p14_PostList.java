/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.packets;

import java.util.ArrayList;
import net.studworld.models.Comment;
import net.studworld.models.Post;

/**
 * Class p14_PostList  with necessary fields.Data packet for transactions with id = 14.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p14_PostList extends Packet {
    private ArrayList<Post> posts = new ArrayList<>();
    private Post query;
    private long offset;

    /**
     *
     */
    public p14_PostList() {
        this.setId(14);
    }

    /**
     * @return the posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * @param posts the posts to set
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    /**
     * @return the query
     */
    public Post getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(Post query) {
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
