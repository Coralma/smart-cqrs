package com.scqrs.core.repository;

import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.scqrs.core.aggregate.Aggregate;

public class Repository {

    private int bufferSize = 1024;
    private RepositoryHandler aggregateEventHandler;
    private Disruptor<AggregateEvent> disruptor;
    
    public Repository() {
    }
    
    public Repository(RepositoryHandler aggregateEventHandler) {
        disruptor = new Disruptor<AggregateEvent>(new AggregateEventFactory(), 
                bufferSize, Executors.newCachedThreadPool(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(aggregateEventHandler);
        disruptor.start();
    }
    
    public void add(Aggregate aggregate) {
        if(aggregate.getUniqueId() == null) {
            throw new RuntimeException("The UniqueId of " + aggregate.getClass().getSimpleName() + " can not be null!");
        }
        RingBuffer<AggregateEvent> ringBuffer = disruptor.getRingBuffer();
        AggregateEventProducer producer = new AggregateEventProducer(ringBuffer);
        producer.onData(aggregate);
    }
    
    public Aggregate load(Object aggregateId) {
        return null;
    }
    
}
