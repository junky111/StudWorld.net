
package net.studworld.models;

/**
 * Model GroupWall with necessary fields. The instance of this class is able to convert to JSON .
 * @extends Group
 * @author Ярослав Кузнецов
 */
public class GroupWall extends Wall{

    /**
     * @return the collaction_name
     */
     private long groupId;
     
    /**
     *
     * @param group
     * @param id
     */
    public GroupWall(Group group, long id){
        this.setId(id);
        this.groupId=group.getId();
    }

    /**
     *
     */
    public GroupWall(){}

    /**
     *
     * @return
     */
    public static String getCollactionName() {
        return "groupwalls";
    }

    /**
     * @return the groupId
     */
    public long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
