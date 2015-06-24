package net.studworld.packets;

import net.studworld.models.JSONObject;

/**
 * Class Packet  with necessary fields.Data packet for transactions.
 * @extends JSONObject.
 * @author Ярослав Кузнецов
 */
public class Packet extends JSONObject {
    public String hash = "",

    /**
     *
     */
    userHash = "",

    /**
     *
     */
    userId = "";

    /**
     *
     */
    public long time = System.currentTimeMillis()/1000;

    /**
     *
     */
    public char action='c';//r, u, d
    public boolean result = true,

    /**
     *
     */
    self = false,

    /**
     *
     */
    logged = false;
}
