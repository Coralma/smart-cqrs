package com.scqrs.sample.event;

import com.scqrs.core.annotation.Event;
import com.scqrs.core.annotation.UniqueId;

@Event(bufferSize = 4096)
public class HelloEvent {

	@UniqueId
	private String uniqueId;
	
    private String value;
    
//    public HelloEvent(String value) {
//        this.value = value;
//    }

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
