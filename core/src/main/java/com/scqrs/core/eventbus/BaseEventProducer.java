package com.scqrs.core.eventbus;

import com.lmax.disruptor.RingBuffer;

public class BaseEventProducer {

    private final RingBuffer<BaseEvent> ringBuffer;

    public BaseEventProducer(RingBuffer<BaseEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(Object event) {
        // Grab the next sequence
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor for the sequence
            BaseEvent baseEvent = ringBuffer.get(sequence); 
            baseEvent.setEvent(event); // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
