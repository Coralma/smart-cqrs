package com.scqrs.core.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.lmax.disruptor.EventHandler;
import com.scqrs.core.eventbus.EventBus;

public abstract class AbstractRepositoryHandler implements EventHandler<AggregateEvent> {

    protected EventBus eventBus;
    
    @Override
    public void onEvent(AggregateEvent event, long sequence, boolean endOfBatch)
            throws Exception {
        eventStore(event);
        System.out.println("Store event into repository and send to event bus.");
        eventBus.post(event.getApplyedEvent());
    }
    
    public abstract void eventStore(AggregateEvent event);

    /**
     * @return the eventBus
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * @param eventBus the eventBus to set
     */
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

}
