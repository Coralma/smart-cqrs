package com.scqrs.sample.event;

import com.scqrs.core.annotation.Event;
import com.scqrs.core.annotation.EventType;

@Event(type = EventType.ORDER, bufferSize = 32)
public class ByeEvent {


    private String value;
    
    public ByeEvent() {
        
    }
    
    public ByeEvent(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
