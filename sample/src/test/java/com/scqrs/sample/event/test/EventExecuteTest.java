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
import com.scqrs.core.eventbus.EventFactory;
import com.scqrs.sample.event.HelloEvent;

public class EventExecuteTest extends EventAbstractTest{

    @Autowired
    private EventBus eventBus;
    
    @Test
    public void testEvent() throws Exception {
    	HelloEvent event = EventFactory.createEvent(HelloEvent.class);
    	event.setValue("Hello world for eventBus!");
        eventBus.post(event);
    }
}
