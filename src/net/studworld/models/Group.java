
package net.studworld.models;


import java.util.ArrayList;
import net.studworld.system.MongoDBJDBC;

/**
 * Model Group with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */
public class Group extends MongoDBObject{
    private String name;
    private String img ;
    private String description;  
    private ArrayList subscribers;
    
    /**
     *
     * @param name
     * @param img
     * @param description
     */
    public Group(String name, String img, String description){
        this.name=name;
        this.img=img;
        this.description=description;
    }

    /**
     *
     */
    public Group() {
        this.setCollectionName("groups");
    }
    
    /**
     *
     * @param id
     * @return
     */
    public boolean subscribe(long id){
        return subscribers.add(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean unsubscribe(long id){
        return subscribers.remove(id);
    }
    
    /**
     *
     * @param id
     */
    public void addSubscriber(long id){
        this.subscribe(id);
        //this.save();
    }

    /**
     *
     * @param id
     */
    public void deleteSubscriber(long id){
        this.unsubscribe(id);
        //this.save();
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the subscribers
     */
    public ArrayList getSubscribers() {
        return subscribers;
    }

    /**
     * @param subscribers the subscribers to set
     */
    public void setSubscribers(ArrayList subscribers) {
        this.subscribers = subscribers;
    }
}
