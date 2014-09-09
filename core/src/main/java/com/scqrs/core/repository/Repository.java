package com.scqrs.core.repository;

public interface Repository<T> {

    public T load(Object aggregateId);
    
    public void add(T aggregate);
}
