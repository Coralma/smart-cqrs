package com.scqrs.core.aggregate;

public interface Aggregate {

    public Object getApplyedEvent();
    
    public Object getUniqueId();
}
