package com.scqrs.sample.command.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.scqrs.core.commandbus.CommandBus;
import com.scqrs.sample.command.CreateUserCommand;

public class CommandExecuteTest extends CommandAbstractTest {

    @Autowired
    private CommandBus commandBus;
    
    @Test
    public void testEvent() throws Exception {
        commandBus.post(new CreateUserCommand());
    }
}