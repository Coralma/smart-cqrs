package com.scqrs.core.eventbus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.scqrs.core.annotation.EventHandler;
import com.scqrs.core.annotation.EventType;
import com.scqrs.core.util.EventUtils;

/**
 * EventBusPostProcessor registers Spring beans with EventBus. 
 * All beans containing @EventBus annotation are registered. 
 * It only used in eventbus-context.xml.
 */
public class EventBusPostProcessor implements BeanPostProcessor {

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
                if (annotation.annotationType().equals(EventHandler.class)) {
                    Class<?>[] types = method.getParameterTypes();
                    if(types.length != 1) {
                        throw new RuntimeException("The eventHandler must define only one event.");
                    }
                    Class<?> type = types[0];
                    EventType eventType = EventUtils.getEventType(type);
                    // if the eventType is order, the eventBus will keep the order index.
                    if(eventType.equals(EventType.ORDER)) {
                        EventHandler eventHanderAnnotation = (EventHandler) annotation;
                        int order = eventHanderAnnotation.order();
                        // register it with the event bus with order
                        eventBus.register(type, bean, method.getName(),order);
                    } else {
                        // register it with the event bus
                        eventBus.register(type, bean, method.getName());
                    }
                    log.trace("Bean {} containing method {} was subscribed to {}",
                            new Object[] { beanName, method.getName(),
                                    EventBus.class.getCanonicalName() });
                    return bean;
                }
            }
        }

        return bean;
    }

    @Autowired
    private EventBus eventBus;
}