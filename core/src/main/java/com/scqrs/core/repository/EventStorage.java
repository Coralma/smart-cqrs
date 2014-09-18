package com.scqrs.core.repository;

import com.lmax.disruptor.EventHandler;
import com.scqrs.core.aggregate.Aggregate;

public interface EventStorage extends EventHandler<AggregateEvent> {
    
//    public Aggregate load(Object aggregateId, Class<?> aggregateClass);
    public <T extends Aggregate> T load(Object aggregateId, Class<T> aggregateClass);
}
