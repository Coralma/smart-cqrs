package com.scqrs.sample.command.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scqrs.core.annotation.CommandHandler;
import com.scqrs.core.repository.Repository;
import com.scqrs.sample.aggregate.UserAggregate;
import com.scqrs.sample.command.ChangePasswordCommand;
import com.scqrs.sample.command.CreateUserCommand;

@Component
public class UserCommandHandler {
    
    @Autowired
    public Repository repository;

    @CommandHandler
    public void handlerCreateUser(CreateUserCommand createUserCommand) {
        System.out.println("Run in UserCommandHandler.handlerCreateUser");
        UserAggregate aggregate = new UserAggregate(createUserCommand);
        repository.add(aggregate);
    }
    
//    @CommandHandler
//    public void handlerCreateUser(ChangePasswordCommand changePasswordCommand) {
//        System.out.println("Run in UserCommandHandler.handlerCreateUser");
//        UserAggregate aggregate = (UserAggregate) repository.load(changePasswordCommand.getUniqueId(), UserAggregate.class);
//        aggregate.createUserAggregate(createUserCommand);
//        repository.add(aggregate);
//    }
}
