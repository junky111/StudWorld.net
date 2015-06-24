/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.config;

import net.studworld.models.Server;

/**
 *
 * @author administrator
 */
public class ExampleServer extends Server {

    /**
     *
     */
    public ExampleServer() {
        super();
        this.setHost("123.456.7.89");
        this.setPort((short)8084);
        this.setId(1);
    }

    /**
     *
     */
    @Override
    public void beforeStart() {}

    /**
     *
     */
    @Override
    public void afterStart() {}
}
