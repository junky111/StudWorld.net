/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.RabbitMQ;

import net.studworld.models.JSONObject;

/**
 *
 * @author Ярослав
 * @param <T>
 */
public interface IModel <T extends JSONObject>{

    /**
     *
     */
    void cache();

    /**
     *
     * @param model
     */
    void setCache(IModel <T>model);
}
