/**
 * 
 */
package com.scqrs.sample.eventhandler;

import org.springframework.stereotype.Component;

import com.scqrs.core.annotation.EventHandler;
import com.scqrs.sample.event.HelloEvent;

/**
 * @author Coral
 *
 */
@Component
public class HelloEventHandler {

    @EventHandler
    public void handleHelloEvent(HelloEvent event) {
        System.out.println("HelloEventHandler: " + event.getValue());
    }
}
