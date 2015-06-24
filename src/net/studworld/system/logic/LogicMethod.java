
package net.studworld.system.logic;

import java.io.IOException;
import net.studworld.system.RabbitMQPushException;

/**
 *  Abstract class LogicMethod class for adding the logic for package, where id=0;
 * @extends LogicMethod
 * @author Ярослав
 */
public abstract class LogicMethod {
    private int id;
    private LogicMethod nextMethod;

    /**
     *
     * @param sender
     * @param message
     * @throws IOException
     * @throws RabbitMQPushException
     */
    public abstract void logic(Object sender, String message) throws IOException, RabbitMQPushException;

    /**
     *
     * @param method
     */
    public void setNextMethod(LogicMethod method) {
        this.nextMethod = method;
    }

    /**
     *
     * @param sender
     * @param id
     * @param message
     * @throws IOException
     * @throws RabbitMQPushException
     */
    public void run(Object sender, long id, String message) throws IOException, RabbitMQPushException {
        if(this.getId()==id) {
            this.logic(sender, message);
        } else {
            if(this.nextMethod!=null) {
                this.nextMethod.run(sender, id, message);
            } else {
                System.out.println("unknown packet "+message);
            }
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param method
     */
    public void add(LogicMethod method) {
        if(this.nextMethod==null) {
            this.nextMethod = method;
        } else {
            this.nextMethod.add(method);
        }
    }
}
