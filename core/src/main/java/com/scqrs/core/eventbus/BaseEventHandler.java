package com.scqrs.core.eventbus;

import java.lang.reflect.Method;

import com.lmax.disruptor.EventHandler;
import com.scqrs.core.annotation.EventType;

public class BaseEventHandler implements EventHandler<BaseEvent> {

    private EventType eventType;
    private RegistedEventHandler[] registedEventHandlers;
    
    public BaseEventHandler(EventType eventType, RegistedEventHandler... registedEventHandlers) {
        this.eventType = eventType;
        this.registedEventHandlers = registedEventHandlers;
    }
    
    public void onEvent(BaseEvent event, long sequence, boolean endOfBatch) {
        Object eventObject = event.getEvent();
        if(EventType.ORDER.equals(eventType)) {
            for(RegistedEventHandler eventHandler : registedEventHandlers) {
                execute(eventObject, eventHandler);
            }
        } else {
           execute(eventObject, registedEventHandlers[0]); 
        }
    }
    
    private void execute(Object eventObject, RegistedEventHandler registedEventHandler) {
        Object eventHandler = registedEventHandler.getEventHandlerBean();
        Class<?> eventHandlerClass = eventHandler.getClass();
        String methodName = registedEventHandler.getMethod();
        Class<?> type = registedEventHandler.getParameterType();
        try {
            Method method = eventHandlerClass.getMethod(methodName, type);
            method.invoke(eventHandler, eventObject);
        } catch (Exception e) {
            throw new RuntimeException("EventHandler initialize error with"
                    + " the eventHandlerClass is " + eventHandlerClass
                    + ", the method is " + methodName
                    + ", the type is " + type);
        }        
    }

}
