package net.studworld.models;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import net.studworld.system.StudWorld;

/**
 * Model Article with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */
public class Article extends MongoDBObject {
    
    public static short NEW = 0,

    /**
     *
     */
    UPDATE = 1,

    /**
     *
     */
    DELETE = 2;
    
    private String content;
    private long userId;
    private long blogId;
    private transient Like like;
    private ArrayList likes;
    private long time;   //???? 

    private transient User user;
    private transient Blog blog;
    private transient Comment comment;
    /**
     *@constructor Article  
     * @param user  
     * @param blog
     * @param content
     * @param id
     * @param like 
     */
    public Article(User user, Blog blog, String content, long id, Like like) {
        this.setId(id);
        this.content=content;
        this.userId=user.getId();
        this.blogId=blog.getId();
        this.likes=like.getLikes();
    }
    /**
     * @constructor Article
     * @param user
     * @param blog
     * @param content
     * @param id 
     */
    public Article(User user, Blog blog, String content, long id) {
        this.setId(id);
        this.content=content;
        this.userId=user.getId();
        this.blogId=blog.getId();
    }
    
    /**
     * Use to take the necessary number of articles of some user.
     * @param count. The necessary number of articles.
     * @return  the list of articles.
     */
    public ArrayList<Article> showLastArticlesByUser(int count){
        ArrayList <Article> obj = new ArrayList();
        DBCollection col = StudWorld.MongoDB.getCollection(getCollectionName());
        DBCursor cursor = col.find((DBObject) JSON.parse("{userId: "+this.user.getId()+"}")).limit(count);
        while(cursor.hasNext()){
            obj.add((Article) cursor.next());
        }
        return obj; 
    }
    
   /**
     * Use to take the necessary number of articles of some blog.
     * @param count. The necessary number of articles.
     * @return  the list of articles.
     */
    public ArrayList<Article> showLastArticlesByBlog(int count){
        ArrayList <Article> obj = new ArrayList();
        DBCollection col = StudWorld.MongoDB.getCollection(getCollectionName());
        DBCursor cursor = col.find((DBObject) JSON.parse("{blogId: "+this.blog.getId()+"}")).limit(count);
        while(cursor.hasNext()){
            obj.add((Article) cursor.next());
        }
        return obj; 
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the blog
     */
    public Blog getBlog() {
        return blog;
    }

    /**
     * @param blog the blog to set
     */
    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    /**
     * @return the blogId
     */
    public long getBlogId() {
        return blogId;
    }

    /**
     * @param blogId the blogId to set
     */
    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    /**
     * @return the likes
     */
    public ArrayList getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(ArrayList likes) {
        this.likes = likes;
    }

    /**
     * @return the comment
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

    /**
     * @return the like
     */
    public Like getLike() {
        return like;
    }

    /**
     * @param like the like to set
     */
    public void setLike(Like like) {
        this.like = like;
    }
    /**
     * 
     * @return collectionName  the collectionName to get.
     */
    @Override
    public String getCollectionName() {
        return "articles";
    }
    
    
}
