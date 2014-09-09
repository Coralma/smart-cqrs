package com.scqrs.core.commandbus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.scqrs.core.annotation.CommandHandler;

public class CommandBusPostProcessor implements BeanPostProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        // for each method in the bean
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            // check the annotations on that method
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                // if it contains the Subscribe annotation
                if (annotation.annotationType().equals(CommandHandler.class)) {
                    Class<?>[] types = method.getParameterTypes();
                    // currently the command only support one parameter.
                    Class<?> type = types[0];
                    RegistedCommandHandler commandHandler = new RegistedCommandHandler();
                    commandHandler.setCommandHandlerBean(bean);
                    commandHandler.setMethod(method.getName());
                    commandHandler.setParameterTypes(types);
                    commandBus.register(type, commandHandler);
                    return bean;
                }
            }
        }

        return bean;
    }

    @Autowired
    private CommandBus commandBus;
}
