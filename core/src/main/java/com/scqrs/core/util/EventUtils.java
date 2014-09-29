package com.scqrs.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.UUID;

import org.springframework.util.Assert;

import com.scqrs.core.annotation.Event;
import com.scqrs.core.annotation.EventType;
import com.scqrs.core.annotation.UniqueId;

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
    
    public static Object getUniqueId(Object event) {
    	Assert.notNull(event);
    	for(Field field : event.getClass().getDeclaredFields()) {
    		for(Annotation annotation : field.getDeclaredAnnotations()) {
    			if (annotation.annotationType().equals(UniqueId.class)) {
                    try {
                    	field.setAccessible(true);
						return field.get(event);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
                }
    		}
    	}
    	return null;
    }
    
    public static Object initUniqueId(Object event) {
    	Assert.notNull(event);
    	for(Field field : event.getClass().getDeclaredFields()) {
    		for(Annotation annotation : field.getDeclaredAnnotations()) {
    			if (annotation.annotationType().equals(UniqueId.class)) {
                    try {
                    	field.setAccessible(true);
						field.set(event, generateUniqueId());
						return event;
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
                }
    		}
    	}
    	return null;
    }
    
    public static Object generateUniqueId() {
    	return UUID.randomUUID().toString();
    }
}
