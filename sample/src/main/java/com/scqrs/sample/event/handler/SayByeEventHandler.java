package com.scqrs.sample.event.handler;

import org.springframework.stereotype.Component;

import com.scqrs.core.annotation.EventHandler;
import com.scqrs.sample.event.ByeEvent;

@Component
public class SayByeEventHandler {
    
    @EventHandler(order = 2)
    public void sayByeEvent(ByeEvent byeEvent) {
        System.out.println("ByeEventHandler.sayByeEvent : " + byeEvent.getValue());
    }
}
