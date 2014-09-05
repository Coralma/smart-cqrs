package com.scqrs.core.eventbus;

public class RegistedEventHandler {

    private Object eventHandlerBean;
    private String method;
    private Class<?> parameterType;
    
    public RegistedEventHandler(Object bean, String method, Class<?> parameterType) {
        this.eventHandlerBean = bean;
        this.method = method;
        this.parameterType = parameterType;
    }
    /**
     * @return the eventHandlerBean
     */
    public Object getEventHandlerBean() {
        return eventHandlerBean;
    }
    /**
     * @param eventHandlerBean the eventHandlerBean to set
     */
    public void setEventHandlerBean(Object eventHandlerBean) {
        this.eventHandlerBean = eventHandlerBean;
    }
    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }
    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }
    /**
     * @return the parameterType
     */
    public Class<?> getParameterType() {
        return parameterType;
    }
    /**
     * @param parameterType the parameterType to set
     */
    public void setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
    }
}
