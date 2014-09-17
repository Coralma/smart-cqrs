package com.scqrs.sample.event.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Stopwatch;
import com.scqrs.core.eventbus.EventBus;
import com.scqrs.sample.event.HelloEvent;

public class EventExecuteTest extends EventAbstractTest{

    @Autowired
    private EventBus eventBus;
    
    @Test
    public void testEvent() throws Exception {
        eventBus.post(new HelloEvent("Hello world!"));
        
        // performance test
        Stopwatch sw = Stopwatch.createStarted(); 
        int number = 1000000;
        for(int i=0; i < number; i++) {
            eventBus.post(new HelloEvent("Performance test! Number is : " + i));
        }
        System.out.println("***Post " + number + " posts take time: " + sw);
        Thread.sleep(100000l);
    }
}
