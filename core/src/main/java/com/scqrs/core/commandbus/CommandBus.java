package com.scqrs.core.commandbus;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

public class CommandBus {

    private final ConcurrentMap<Class<?>, RegistedCommandHandler> commandHandlerPool = Maps.newConcurrentMap();

    public CommandBus() {
        System.out.println("init CommandBus.");
    }
    
    public void post(Object command) {
        Class<?> commandClass = command.getClass();
        RegistedCommandHandler registedCommandHandler = commandHandlerPool.get(commandClass);
        if(registedCommandHandler == null) {
            new RuntimeException("The command handler of " + commandClass + " didn't define.");
        }
        execute(command, registedCommandHandler);
    }

    public void register(Class<?> commandClass, RegistedCommandHandler handler) {
        RegistedCommandHandler registedCommandHandler = commandHandlerPool.get(commandClass);
        if(registedCommandHandler == null) {
            commandHandlerPool.put(commandClass, handler);    
        } else {
            new RuntimeException("Each commandClass only allow to define one commandHandler, please check the handler definition of " + commandClass);
        }
        
    }

    private void execute(Object eventObject, RegistedCommandHandler registedCommandHandler) {
        Object eventHandler = registedCommandHandler.getCommandHandlerBean();
        Class<?> eventHandlerClass = eventHandler.getClass();
        String methodName = registedCommandHandler.getMethod();
        Class<?>[] types = registedCommandHandler.getParameterTypes();
        try {
            Method method = eventHandlerClass.getMethod(methodName, types);
            method.invoke(eventHandler, eventObject);
        } catch (Exception e) {
            throw new RuntimeException("EventHandler initialize error with"
                    + " the eventHandlerClass is " + eventHandlerClass
                    + ", the method is " + methodName
                    + ", the type is " + types);
        }        
    }
}
