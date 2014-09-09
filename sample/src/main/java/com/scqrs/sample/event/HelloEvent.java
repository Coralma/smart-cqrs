package com.scqrs.sample.event;

import com.scqrs.core.annotation.Event;

@Event
public class HelloEvent {

    private String value;
    
    public HelloEvent() {
        
    }
    
    public HelloEvent(String value) {
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
