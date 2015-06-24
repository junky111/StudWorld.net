package net.studworld.models;

import net.studworld.system.StudWorld;


/**
 * Model JSONObject with necessary fields. Makes the possibility to convert objects to JSON.
 * @author Ярослав Кузнецов
 */
public class JSONObject {

    /**
     *
     */
    public long id;
    
    /**
     * Converts  java objects to JSON objects.
     * @return objects in JSON.
     */
    public String toJson() {
        return StudWorld.Gson.toJson(this);
    }
    
    /**
     *  Converts from JSON string to Java object of necessary type.
     * @param <T> instance of any class which extends JSONObject
     * @param json  JSON string.
     * @param c any class which extends JSONObject
     * @return  instance of < T > class , converted from JSON string.
     */
    public static <T extends JSONObject> T fromJson(String json, Class<T> c) {
        return (T) StudWorld.Gson.fromJson(json, c);
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }


}
