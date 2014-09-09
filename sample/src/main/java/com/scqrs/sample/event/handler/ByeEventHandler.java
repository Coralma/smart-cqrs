package com.scqrs.sample.event.handler;

import org.springframework.stereotype.Component;

import com.scqrs.core.annotation.EventHandler;
import com.scqrs.sample.event.ByeEvent;

@Component
public class ByeEventHandler {

    @EventHandler(order = 1)
    public void handleByeEvent(ByeEvent byeEvent) {
        System.out.println("ByeEventHandler.handleByeEvent : " + byeEvent.getValue());
    }
    
}
