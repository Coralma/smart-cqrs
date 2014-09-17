package com.scqrs.core.aggregate;

public abstract class AbstractAggregate implements Aggregate {

    private Object applyedEvent;
    
    public void apply(Object applyedEvent) {
        this.applyedEvent = applyedEvent;
    }
    
    public Object getApplyedEvent() {
        return applyedEvent;
    }
}
