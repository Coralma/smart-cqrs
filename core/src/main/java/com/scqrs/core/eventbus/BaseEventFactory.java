package com.scqrs.core.eventbus;

import com.lmax.disruptor.EventFactory;

public class BaseEventFactory implements EventFactory<BaseEvent> {

    public BaseEvent newInstance() {
        return new BaseEvent();
    }
}
