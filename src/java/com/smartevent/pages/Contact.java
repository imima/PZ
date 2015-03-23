/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.pages;

import com.smartevent.entities.Message;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 *
 * @author Mima
 */
public class Contact {

    @Inject
    private Session hibernate;
    @Property
    private Message contactMessage = new Message();
    
    @Persist("Flash")
    @Property
    private Message message;

    @CommitAfter
    public void onActionFromSendMessage() {
        System.out.println("Send Message Action");
        hibernate.save(contactMessage);
    }
}
