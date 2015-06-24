package net.studworld.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import net.studworld.system.StudWorld;
/**
 * Abstract Сonfiguration class for the configuration of the server of any type.
 * @author Ярослав Кузнецов
 */
public abstract class Config {

    /**
     *
     */
    public transient String configName;

    /**
     *
     */
    public Access rabbitMQData = new Access();

    /**
     *
     */
    public short queueWorkerNum = 1;
    /**
     * Design pattern  -  Abstract Factory. 
     * Read  configuration data from the disk.
     * @param c
     * @return instance of Config class.
     * @throws IOException mistake while reading  data from the disk
     */
    public Config instantiate(Class c) throws IOException {
        
        try{
            BufferedReader br = new BufferedReader(new FileReader("./"+this.configName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Config o = (Config) StudWorld.Gson.fromJson(everything, c);
            String prettyJsonString = gson.toJson(o);
            try (PrintWriter writer = new PrintWriter("./"+this.configName, "UTF-8")) {
                writer.println(prettyJsonString);
            }
            return o;
        } catch(Exception e) {
            e.printStackTrace();
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(StudWorld.Gson.toJson(this));
                String prettyJsonString = gson.toJson(je);
                try (PrintWriter writer = new PrintWriter("./"+this.configName, "UTF-8")) {
                    writer.println(prettyJsonString);
                }
                return this;
            } catch (IllegalArgumentException | SecurityException ex) {
                throw new IOException();
            }
        }
    }
}
