package com.scqrs.sample.command.handler;

import org.springframework.stereotype.Component;

import com.scqrs.core.annotation.CommandHandler;
import com.scqrs.sample.command.CreateUserCommand;

@Component
public class UserCommandHandler {

    @CommandHandler
    public void handlerCreateUser(CreateUserCommand createUserCommand) {
        System.out.println("Run handlerCreateUser.");
    }
}
