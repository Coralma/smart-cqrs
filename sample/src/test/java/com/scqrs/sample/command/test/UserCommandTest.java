package com.scqrs.sample.command.test;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scqrs.core.commandbus.CommandBus;
import com.scqrs.sample.command.CreateUserCommand;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/application-context.xml"})
public class UserCommandTest {

    @Autowired
    public CommandBus commandBus;
    
    @Test
    public void testCase() throws Exception {
        CreateUserCommand user = new CreateUserCommand();
        user.setUniqueId(UUID.randomUUID().toString());
        user.setUserName("Coral");
        user.setPassword("Password1");
        commandBus.post(user);
        Thread.sleep(1000000l);
    }
}
