
package net.studworld.webclient;

import java.util.ArrayList;
import java.util.HashMap;
import net.studworld.system.logic.LogicMethod;
/**
 * Class AccessRules class for instaling the access rules.
 * @author Ярослав
 */
public class AccessRules {
    HashMap<Class, ArrayList<Short>> rules = new HashMap<>();
    
    /**
     * Register the rules hash map.
     * @param c any child of the LogicMethod class.
     * @param query 
     */
    public void register(Class<? extends LogicMethod> c, Short query) {
        if(rules.containsKey(c)) {
            rules.get(c).add(query);
        } else {
            ArrayList<Short> list = new ArrayList<>();
            list.add(query);
            rules.put(c, list);
        }
    }
    
    /**
     * Checking the user access.
     * @param c any child of the LogicMethod class.
     * @param query
     * @return  boolean has user access or not.
     */
    public boolean haveAccess(Class<? extends LogicMethod> c, Short query) {
        if(rules.containsKey(c)) {
            return rules.get(c).contains(query);
        }
        return false;
    }
}
