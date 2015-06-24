
package net.studworld.config;

import java.io.IOException;
/**
 * Сonfiguration class for the configuration of the client server.
 * @extends Config
 * @author Ярослав Кузнецов
 */
public class ClientConfig extends Config {

    /**
     *
     */
    public ExampleServer Server = new ExampleServer();
    /**
     * Use for creating the instance of the ClientConfig class. 
     * @return the instance of the ClientConfig class.
     * @throws IOException 
     */
    public ClientConfig instance() throws IOException {
        super.configName = "client.properties";
        return (ClientConfig) super.instantiate(this.getClass());
    }
}
