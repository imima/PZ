/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.rest.interfaces;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 *
 * @author Mima
 */
public interface IEvent {
    
    @Get("xml")
    public Representation getEvents();
    
    @Post
    public Representation postEvent();
}
