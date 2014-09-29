package com.scqrs.core.eventbus;

import com.scqrs.core.exception.ExceptionHandler;
import com.scqrs.core.util.EventUtils;

@SuppressWarnings("unchecked")
public class EventFactory {
	
	public static <T> T createEvent(Class<T> eventClass) {
		try {
			T t = eventClass.newInstance();
			return (T) EventUtils.initUniqueId(t);
		} catch (Exception e) {
			throw ExceptionHandler.newRuntimeException(EventFactory.class, "Create event error. Please check your event class has defined the default constructor and uniqueId.");
		}
	}
}
