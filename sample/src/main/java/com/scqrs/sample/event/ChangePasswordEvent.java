package com.scqrs.sample.event;

import com.scqrs.core.annotation.Event;

@Event
public class ChangePasswordEvent {

    String uniqueId;
    String password;
    public ChangePasswordEvent(String uniqueId, String password) {
        this.uniqueId = uniqueId;
        this.password = password;
    }
    /**
     * @return the uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }
    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
