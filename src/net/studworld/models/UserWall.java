
package net.studworld.models;


/**
 * Model UserWall with necessary fields. The instance of this class is able to convert to JSON .
 * @extends Wall
 * @author Ярослав Кузнецов
 */
public class UserWall extends Wall{
    private long userId;

    /**
     *
     * @param user
     * @param id
     */
    public UserWall(User user, long id){
        this.setId(id);
        this.userId=user.getId();
    }
    
    /**
     *
     */
    public UserWall(){}
    @Override
    public String getCollectionName(){
        return "userwalls";
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
    
}
