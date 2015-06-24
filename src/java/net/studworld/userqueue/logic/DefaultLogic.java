
package net.studworld.userqueue.logic;



import net.studworld.system.logic.LogicMethod;

/**
 *  Class DefaultLogic class for adding the logic for package, where id=0;
 * @extends LogicMethod
 * @author Ярослав
 */
public class DefaultLogic extends LogicMethod {

    /**
     *
     */
    public DefaultLogic() {
        this.setId(0);
    }

           /**
     * Execute logic. Send task  to the queue.
     * @param sender  Sender object
     * @param message Object in JSON 
     */
    @Override
    public void logic(Object sender, String message) {
        System.out.println("default logic");
    }
    
}

