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
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Mima
 */
@ProtectedPage(role=Role.USER)
public class ViewEvents {
    
    @Inject
    private EventService eventService;
    
    @Persist(org.apache.tapestry5.PersistenceConstants.FLASH)
    @Property
    private String eventCreatedTitle;
    @Inject
    private Messages messages;
    @Property
    private Event event;
    @InjectPage
    private CreateEvent createEvent;
    
    public List<Event> getEvents() {
        return eventService.getEvents();
    }
    
    public void eventTitle(String title) {
        this.eventCreatedTitle = title;
    }
    
    public String getEventCreatedMessage() {
        return eventCreatedTitle != null && !"".equals(eventCreatedTitle) ? messages.format("page_view_events_event_created_message", eventCreatedTitle) : "";
    } 
    
    public boolean isEventListEmpty() {
        return !eventService.getEvents().isEmpty() ? false : true;
    }
    
    public Object onActionFromEdit(String eventId) {
        if(eventId != null && !"".equals(eventId)) {
            createEvent.editEvent(eventId);
            return createEvent;
        }
        return null;
    }
    
    @CommitAfter
    public Object onActionFromDelete(String eventId) {
        if(eventId != null && !"".equals(eventId)) {
            eventService.deleteEvent(eventId);
        }
        return null;
    }
}
