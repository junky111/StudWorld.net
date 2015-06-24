
package net.studworld.config;

import java.io.IOException;

/**
 * Сonfiguration class for the configuration of the server.
 * @extends Config
 * @author Ярослав Кузнецов
 */
public class ServerConfig extends Config {
    public DBAccess mongoAccess = new DBAccess(),

    /**
     *
     */
    mysqlAccess = new DBAccess();
   /**
     * Use for creating the instance of the ServerConfig class. 
     * @return the instance of the ServerConfig class.
     * @throws IOException 
     */
    public ServerConfig instance() throws IOException {
        super.configName = "server.properties";
        return (ServerConfig) super.instantiate(this.getClass());
    }
}