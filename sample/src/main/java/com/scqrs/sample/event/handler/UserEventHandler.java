package com.scqrs.sample.event.handler;

import org.springframework.stereotype.Component;

import com.scqrs.core.annotation.EventHandler;
import com.scqrs.sample.event.CreateUserEvent;

@Component
public class UserEventHandler {

    @EventHandler
    public void createUserEvent(CreateUserEvent createUserEvent) {
        System.out.println("CreateUserEventHandler.createUserEvent : " + createUserEvent);
    }
    
}
