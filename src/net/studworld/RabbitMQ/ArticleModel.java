/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.RabbitMQ;

import java.util.ArrayList;
import net.studworld.models.Article;
import net.studworld.models.Blog;
import net.studworld.models.Comment;
import net.studworld.models.Like;
import net.studworld.models.User;
import net.studworld.packets.p6_Comment;


/**
 *
 * @author Ярослав
 */
public class ArticleModel extends Article implements IModel<ArticleModel> {

    /**
     *
     */
    public ArticleModel articleModel ;
   
    /**
     *
     * @param user
     * @param blog
     * @param content
     * @param id
     * @param like
     */
    public ArticleModel(User user, Blog blog, String content, long id, Like like) {
        super(user, blog, content, id, like);
    }

    /**
     *
     * @param id
     * @return
     */
    public Article getArticle(long id){
        return this;
    }
    /*public p6_Comment[] getComments(){
        p6_Comment[] comment = new p6_Comment[20]; 
        ArrayList<Comment> comments = this.showLastComments(20);
        for(int i =0 ; i < comments.size(); i++){
            Comment com = comments.iterator().next();
            comment[i].setId(com.getId());
            comment[i].setContent(com.getContent());
            comment[i].setFromUserId(com.getUserId());
            comment[i].setToItemId(this.getId());
            comment[i].setItemType(1);
        }
        return comment;
    }*/
    
    /**
     *
     * @return
     */
    public ArrayList<Article> getArticles (){
        return null;
        
    }

    /**
     *
     */
    @Override
    public void cache() {
       ModelProxy model = new ModelProxy((IModel<ArticleModel>)new ArticleModel(this.getUser(), this.getBlog(), this.getContent(), this.getId(),this.getLike()));
    }

    /**
     *
     * @param model
     */
    @Override
    public void setCache(IModel model) {
       this.articleModel= (ArticleModel)model;
    }
    
    /**
     *
     * @return
     */
    public IModel getCache(){
        return this.articleModel;
    }
    
}
