package com.scqrs.sample.event.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Stopwatch;
import com.scqrs.core.eventbus.EventBus;
import com.scqrs.sample.event.ByeEvent;
import com.scqrs.sample.event.HelloEvent;


public class EventOrderExecuteTest extends EventAbstractTest {
    
    @Autowired
    private EventBus eventBus;
    
    @Test
    public void testEvent() throws Exception {
        eventBus.post(new ByeEvent("Bye Bye!"));
        
        // performance test
        Stopwatch sw = Stopwatch.createStarted(); 
        int number = 100;
        for(int i=0; i < number; i++) {
            eventBus.post(new ByeEvent("Performance test! Number is : " + i));
        }
        System.out.println("***Post " + number + " posts take time: " + sw);
        Thread.sleep(10000l);
    }

}
