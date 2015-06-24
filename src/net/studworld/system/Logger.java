

package net.studworld.system;

/**
 * Class Logger  uses to search the system errors.
 * @author Ярослав
 */
public class Logger {
    /**
     * Uses for debuging.
     * @param e  system exception.
     */
    public void log(Exception e) {
        System.err.println(e.getLocalizedMessage());
        e.printStackTrace();
    }
}
