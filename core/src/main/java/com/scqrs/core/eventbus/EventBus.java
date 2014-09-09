/**
 * 
 */
package com.scqrs.core.eventbus;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.scqrs.core.annotation.EventType;
import com.scqrs.core.util.EventUtils;

/**
 * @author Coral
 *
 */
public class EventBus {
    
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
//    protected int bufferSize = 1024;
    protected ConcurrentMap<Class<?>, List<RegistedEventHandler>> handlesMap = Maps.newConcurrentMap();
    protected ConcurrentMap<Class<?>, Disruptor<BaseEvent>> disruptorPool = Maps.newConcurrentMap();

    public void register(Class<?> event, Object bean, String method) {
        this.register(event, bean, method, EventType.CONCURRENCY, 0);
    }
    
    public void register(Class<?> event, Object bean, String method, int order) {
        this.register(event, bean, method, EventType.ORDER, order);
    }
    
    private void register(Class<?> event, Object bean, String method, EventType eventType, int order) {
        List<RegistedEventHandler> handlers = handlesMap.get(event);
        if(handlers == null) {
            handlers = Lists.newArrayList();
            handlesMap.put(event, handlers);
        }
        handlers.add(new RegistedEventHandler(bean, method, event, order));
    }
    
    public void post(Object event) {
        Class<?> eventClass = event.getClass();
        Disruptor<BaseEvent> eventDisruptor = disruptorPool.get(eventClass);
        if(eventDisruptor == null) {
            List<RegistedEventHandler> registedEventHandlers = handlesMap.get(eventClass);
            if(registedEventHandlers == null || registedEventHandlers.size() == 0) {
                throw new RuntimeException("The " + eventClass.getSimpleName() + " event dosen't regist any eventHandler.");
            }
            eventDisruptor = createDisruptor(eventClass, registedEventHandlers);
            disruptorPool.put(eventClass, eventDisruptor);
        }
        RingBuffer<BaseEvent> ringBuffer = eventDisruptor.getRingBuffer();
        BaseEventProducer producer = new BaseEventProducer(ringBuffer);
        producer.onData(event);
    }

    protected Disruptor<BaseEvent> createDisruptor(Class<?> eventClass, List<RegistedEventHandler> registedEventHandlers) {
        WaitStrategy waitStrategy = new BlockingWaitStrategy();
        // load the custmized event bufferSize.
        int bufferSize = EventUtils.getEventBufferSize(eventClass);
        Disruptor<BaseEvent> disruptor =new Disruptor<BaseEvent>(new BaseEventFactory(), 
                bufferSize, Executors.newCachedThreadPool(), ProducerType.SINGLE, waitStrategy);
        List<BaseEventHandler> baseEventHandlers = Lists.newArrayList();
        EventType eventType = EventUtils.getEventType(eventClass);
        if(EventType.ORDER.equals(eventType)) {
            List<RegistedEventHandler> orderingHandlers = orderingRegistedEventHandlers(registedEventHandlers);
            baseEventHandlers.add(new BaseEventHandler(EventType.ORDER, orderingHandlers.toArray(new RegistedEventHandler[0])));
        } else if(EventType.CONCURRENCY.equals(eventType)) { 
            for(RegistedEventHandler registedEventHandler : registedEventHandlers) {
                baseEventHandlers.add(new BaseEventHandler(EventType.CONCURRENCY, registedEventHandler));
            }
        } else {
            throw new RuntimeException("The definition of event type is not correct.");
        }
        disruptor.handleEventsWith(baseEventHandlers.toArray(new BaseEventHandler[0]));
        disruptor.start();
        return disruptor;
    }
    
    protected List<RegistedEventHandler> orderingRegistedEventHandlers(List<RegistedEventHandler> registedEventHandlers) {
        Ordering<RegistedEventHandler> orderings = new Ordering<RegistedEventHandler>() {
            @Override
            public int compare(RegistedEventHandler left,
                    RegistedEventHandler right) {
                return left.getOrder() - right.getOrder();
            }
        }; 
        return orderings.immutableSortedCopy(registedEventHandlers);
    }
}
