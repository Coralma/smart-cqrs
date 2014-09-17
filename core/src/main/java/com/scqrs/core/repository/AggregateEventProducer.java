package com.scqrs.core.repository;

import com.lmax.disruptor.RingBuffer;
import com.scqrs.core.aggregate.Aggregate;

public class AggregateEventProducer {

    private final RingBuffer<AggregateEvent> ringBuffer;

    public AggregateEventProducer(RingBuffer<AggregateEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(Aggregate aggregate) {
        // Grab the next sequence
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor for the sequence
            AggregateEvent aggregateEvent = ringBuffer.get(sequence);
            aggregateEvent.setAggregate(aggregate);
            aggregateEvent.setUniqueId(aggregate.getUniqueId());
            aggregateEvent.setApplyedEvent(aggregate.getApplyedEvent());
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
