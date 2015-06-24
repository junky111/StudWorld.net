
package net.studworld.system.logic;
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
     * Add logic for sending int the queue.
     * @param sender
     * @param message 
     */
    @Override
    public void logic(Object sender, String message) {
        System.out.println("default logic executed");
    }
    
}
