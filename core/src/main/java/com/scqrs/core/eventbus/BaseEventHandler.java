package com.scqrs.core.eventbus;

import java.lang.reflect.Method;

import com.lmax.disruptor.EventHandler;

public class BaseEventHandler implements EventHandler<BaseEvent> {

    private RegistedEventHandler registedEventHandler;
    
    public BaseEventHandler(RegistedEventHandler registedEventHandler) {
        this.registedEventHandler = registedEventHandler;
    }
    
    public void onEvent(BaseEvent event, long sequence, boolean endOfBatch) {
//        System.out.println("Using thread: "+Thread.currentThread().getName() + ", id: " + Thread.currentThread().getId());
//        System.out.println("Event Value: " + event.getEvent() +  " , sequence: " + sequence + " , endOfBatch: " + endOfBatch);
        Object eventHandler = registedEventHandler.getEventHandlerBean();
        Object eventObject = event.getEvent();
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
