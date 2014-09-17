package com.scqrs.core.repository;

import com.scqrs.core.aggregate.Aggregate;

public class AggregateEvent {

    public Object uniqueId;
    public Object applyedEvent;
    public Aggregate aggregate;

    /**
     * @return the applyedEvent
     */
    public Object getApplyedEvent() {
        return applyedEvent;
    }

    /**
     * @param applyedEvent the applyedEvent to set
     */
    public void setApplyedEvent(Object applyedEvent) {
        this.applyedEvent = applyedEvent;
    }

    /**
     * @return the uniqueId
     */
    public Object getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(Object uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return the aggregate
     */
    public Aggregate getAggregate() {
        return aggregate;
    }

    /**
     * @param aggregate the aggregate to set
     */
    public void setAggregate(Aggregate aggregate) {
        this.aggregate = aggregate;
    }
}
