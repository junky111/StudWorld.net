
package net.studworld.packets;

import net.studworld.models.Post;

/**
 * Class p5_Post  with necessary fields.Data packet for transactions with id = 5.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p5_Post extends Packet {
    private Post post;

    /**
     *
     */
    public p5_Post() {
        this.setId(5);
    }

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }


}
