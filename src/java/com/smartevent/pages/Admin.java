/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.pages;

import com.smartevent.data.Role;
import com.smartevent.entities.Event;
import com.smartevent.services.business.EventService;
import com.smartevent.services.security.ProtectedPage;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Mima
 */
@ProtectedPage(role=Role.ADMIN)
public class Admin {
    
    @Inject
    private EventService eventService;
    @Property
    private Event event = new Event();
    @Persist("flash")
    private String eventId;
    @Persist
    private Integer eventIndex;
    @Inject
    private Messages messages;
    
    public List<Event> getEvents() {
        return eventService.getEvents();
    }
    
    void onPrepare() throws Exception {
        if(eventId != null) {
            this.event = eventService.getEventById(eventId);
        } else {
            event = new Event();
        }
    }
    
    public Object onActionFromEvent(){
        System.out.println("Create Event Action");
        eventService.createOrEditEvent(event);
        event = null;
        eventId = null;
        return Admin.class;
    }

    @CommitAfter
    public Object onActionFromDelete(String eventId) {
        if(eventId != null && !"".equals(eventId)) {
            eventService.deleteEvent(eventId);
        }
        return null;
    }
    
}
