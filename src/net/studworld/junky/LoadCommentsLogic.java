
package net.studworld.junky;
 
import com.mongodb.DBCursor;
import net.studworld.models.Comment;
import net.studworld.packets.p16_CommentsList;
import net.studworld.system.RabbitMQPushException;
import net.studworld.system.StudWorld;
import net.studworld.system.logic.LogicMethod;


/**
 *  Class LoadCommentsLogic class for adding the logic for package, where id=16;
 * @extends LogicMethod
 * @author Ярослав
 */
public class LoadCommentsLogic extends LogicMethod {
    private int packageSize = 20;

    /**
     *
     */
    public LoadCommentsLogic(){
        this.setId(16);
    }
   /**
     * Execute logic of comments loading 
     * @param sender  Sender object
     * @param message Object in JSON 
     * @throws RabbitMQPushException 
     */
    @Override
    public void logic(Object sender, String message) throws RabbitMQPushException {
        p16_CommentsList p16 = StudWorld.Gson.fromJson(message, p16_CommentsList.class);
        DBCursor cursor = StudWorld.MongoDB.showDocuments(p16.getQuery());
        cursor.skip((int) (p16.getOffset()*packageSize));
        for(int i = 0; i < this.packageSize; i++) {
            if(!cursor.iterator().hasNext()) break;
            p16.getComments().add((Comment) cursor.iterator().next());
        }
        StudWorld.RabbitMQ.sendToUser(p16);
    }
}
