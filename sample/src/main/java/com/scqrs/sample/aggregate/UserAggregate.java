package com.scqrs.sample.aggregate;

import com.scqrs.core.aggregate.AbstractAggregate;
import com.scqrs.core.annotation.AggregateId;
import com.scqrs.sample.command.ChangePasswordCommand;
import com.scqrs.sample.command.CreateUserCommand;
import com.scqrs.sample.event.CreateUserEvent;

public class UserAggregate extends AbstractAggregate {
    
    @AggregateId
    private String id;
    private CreateUserCommand createUserCommand;
    
    public UserAggregate(CreateUserCommand createUserCommand) {
        this.createUserCommand = createUserCommand;
    }
    
    public void createUserAggregate(CreateUserCommand createUserCommand) {
        this.id = createUserCommand.getUniqueId();
        apply(new CreateUserEvent(createUserCommand));
    }
    
    public void changePasswordAggregate(ChangePasswordCommand changePasswordCommand) {
        apply(new CreateUserEvent(createUserCommand));
    }

    @Override
    public Object getUniqueId() {
        return id;
    }
}
