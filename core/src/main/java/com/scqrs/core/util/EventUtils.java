package com.scqrs.core.util;

import java.lang.annotation.Annotation;

import com.scqrs.core.annotation.Event;
import com.scqrs.core.annotation.EventType;

public class EventUtils {

    public static EventType getEventType(Class<?> type) {
        Annotation[] annotations = type.getAnnotations();
        for(Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Event.class)) {
                Event event = (Event)annotation;
                return event.type();
            }
        }
        return null;
    }
    
    public static int getEventBufferSize(Class<?> type) {
        Annotation[] annotations = type.getAnnotations();
        for(Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Event.class)) {
                Event event = (Event)annotation;
                return event.bufferSize();
            }
        }
        return 1024;
    }
    
    public static void execution() {
        
    }
}
