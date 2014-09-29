package com.scqrs.core.repository;

public interface EventRepository {

	public void save(Object event);
	
	public Object loadEvent(Object uniqueId, Class<?> eventClass);
}
