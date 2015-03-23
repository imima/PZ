/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.services.business;

import com.smartevent.entities.Event;
import java.util.List;

/**
 *
 * @author Mima
 */
public interface EventService {
    
    Event getEventById(String eventId);
    List<Event> getEvents();
    void createOrEditEvent(Event event);
    void deleteEvent(String eventId);
}
