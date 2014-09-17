package com.scqrs.sample.event;

import com.scqrs.core.annotation.Event;
import com.scqrs.sample.command.CreateUserCommand;

@Event
public class CreateUserEvent {

    public CreateUserCommand userCommand;
    
    public CreateUserEvent(CreateUserCommand userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * @return the userCommand
     */
    public CreateUserCommand getUserCommand() {
        return userCommand;
    }

    /**
     * @param userCommand the userCommand to set
     */
    public void setUserCommand(CreateUserCommand userCommand) {
        this.userCommand = userCommand;
    }
}