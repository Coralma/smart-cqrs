package com.scqrs.core.eventbus;

public class BaseEvent {

    public Object event;

    /**
     * @return the event
     */
    public Object getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(Object event) {
        this.event = event;
    }
}
