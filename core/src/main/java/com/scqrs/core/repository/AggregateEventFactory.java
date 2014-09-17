package com.scqrs.core.repository;

import com.lmax.disruptor.EventFactory;

public class AggregateEventFactory implements EventFactory<AggregateEvent> {

    @Override
    public AggregateEvent newInstance() {
        return new AggregateEvent();
    }

}
