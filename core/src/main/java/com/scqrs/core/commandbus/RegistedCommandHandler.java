package com.scqrs.core.commandbus;

public class RegistedCommandHandler {

    public Object commandHandlerBean;
    private String method;
    private Class<?>[] parameterTypes;

    /**
     * @return the commandHandlerBean
     */
    public Object getCommandHandlerBean() {
        return commandHandlerBean;
    }

    /**
     * @param commandHandlerBean the commandHandlerBean to set
     */
    public void setCommandHandlerBean(Object commandHandlerBean) {
        this.commandHandlerBean = commandHandlerBean;
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
     * @return the parameterTypes
     */
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    /**
     * @param parameterTypes the parameterTypes to set
     */
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
