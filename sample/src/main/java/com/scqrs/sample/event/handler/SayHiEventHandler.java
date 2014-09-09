package com.scqrs.sample.event.handler;

import org.springframework.stereotype.Component;

import com.scqrs.core.annotation.EventHandler;
import com.scqrs.sample.event.HelloEvent;

/**
 * @author Coral
 *
 */
@Component
public class SayHiEventHandler {

    @EventHandler
    public void handleHelloEvent(HelloEvent event) {
        System.out.println("SayHiEventHandler: " + event.getValue());
        try {
            Thread.sleep(50l);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
