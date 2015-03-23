/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.services.business;

import com.smartevent.dao.EventDAO;
import com.smartevent.entities.Event;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Mima
 */
public class EventServiceImpl implements EventService {

    @Inject
    private EventDAO eventDao;
    
    @Override
    public Event getEventById(String eventId) {
        return eventDao.getById(Integer.valueOf(eventId));
    }
    
    @Override
    public List<Event> getEvents() {
        return eventDao.getAll();
    }

    @Override
    public void createOrEditEvent(Event event) {
        eventDao.saveOrUpdate(event);
    }

    @Override
    public void deleteEvent(String eventId) {
        eventDao.delete(Integer.valueOf(eventId));
    }
}
