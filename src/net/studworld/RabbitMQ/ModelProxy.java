/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.RabbitMQ;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.studworld.models.JSONObject;

/**
 *
 * @author Ярослав
 * @param <T>
 */
public class ModelProxy<T extends JSONObject> implements InvocationHandler {
    private final IModel<T> model ;

    /**
     *
     * @param model
     */
    public ModelProxy(IModel<T> model){
        this.model=model;
    }
    

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        for (Class<?> interf : model.getClass().getInterfaces()) {
            if (declaringClass.isAssignableFrom(interf)) {
                try {
                    return method.invoke(model, args);
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
            }
        }
        return null;
    }

    /**
     *
     * @param <T>
     * @param model
     * @param ref
     * @return
     */
    public <T extends JSONObject> IModel<T> newInstanse(IModel model, Class<?> ...ref) {
        return (IModel<T>)Proxy.newProxyInstance(model.getClass().getClassLoader()
        ,ref, new ModelProxy(model));
    }
}
    
    
    

