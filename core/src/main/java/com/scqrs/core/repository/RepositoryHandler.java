package com.scqrs.core.repository;

import com.lmax.disruptor.EventHandler;
import com.scqrs.core.aggregate.Aggregate;

public interface RepositoryHandler extends EventHandler<AggregateEvent> {
    
    public Aggregate load(Object aggregateId);
}
