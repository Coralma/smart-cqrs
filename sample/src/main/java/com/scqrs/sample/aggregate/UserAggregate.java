package com.scqrs.sample.aggregate;

import com.scqrs.core.aggregate.AbstractAggregate;
import com.scqrs.core.annotation.AggregateId;
import com.scqrs.sample.command.CreateUserCommand;
import com.scqrs.sample.event.CreateUserEvent;

public class UserAggregate extends AbstractAggregate {
    
    @AggregateId
    private String id;

    public UserAggregate() {
    }
    
    public void createUserAggregate(CreateUserCommand createUserCommand) {
        this.id = createUserCommand.getUniqueId();
        apply(new CreateUserEvent(createUserCommand));
    }

    @Override
    public Object getUniqueId() {
        return id;
    }
}
