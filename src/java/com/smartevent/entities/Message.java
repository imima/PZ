/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.tapestry5.beaneditor.Validate;

/**
 *
 * @author Mima
 */
@Entity
@Table(name = "Message")
public class Message extends EntityGenericBase<Integer> {
    
    @Validate("required")
    @Column(name = "name")
    private String name;
    
    @Validate("required, email")
    @Column(name = "email")
    private String email;
    
    @Validate("required")
    @Column(name = "message")
    private String message;

    public Message() {
    }

    public Message(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
