/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.rest.resource;

import com.smartevent.entities.Event;
import com.smartevent.rest.RestRouter;
import com.smartevent.rest.interfaces.IEvent;
import com.smartevent.services.business.EventService;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tapestry5.services.Request;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Mima
 */
public class EventResource extends ServerResource implements IEvent {

    private EventService eventService;
    
    @Override
    public Representation getEvents() {
        eventService = ((RestRouter) getApplication()).getService(EventService.class);
        try {
            DomRepresentation representation = new DomRepresentation(MediaType.TEXT_XML);

            // Generate a DOM document representing the list of  
            // items.  
            Document d = representation.getDocument();
            Element r = d.createElement("events");
            d.appendChild(r);
            for (Event event : eventService.getEvents()) {
                Class c = event.getClass();
                Element eltItem = d.createElement(c.getSimpleName());
                for (Method m : c.getMethods()) {
                    if (m.getName().startsWith("get") && m.getName() != "getClass" && m.getReturnType().getName() != "java.util.List") {
                        try {
                            Element elt = d.createElement(m.getName().substring(3));
                            elt.appendChild(d.createTextNode("" + m.invoke(event, null)));
                            eltItem.appendChild(elt);

                        } catch (IllegalAccessException ex) {
                        } catch (InvocationTargetException ex) {
                        }
                    }
                }

                r.appendChild(eltItem);
            }
            d.normalizeDocument();

            // Returns the XML representation of this document.  
            return representation;
        } catch (IOException e) {
            Logger.getLogger(EventResource.class.getName()).log(Level.SEVERE, "IOException", e);
        }
        return null;
    }
 
    @Override
    public Representation postEvent() {
        eventService = ((RestRouter) getApplication()).getService(EventService.class);
        
        String title = (String) this.getRequest().getAttributes().get("title");
        String description = (String) this.getRequest().getAttributes().get("description");
        String date = (String) this.getRequest().getAttributes().get("date");
        String time = (String) this.getRequest().getAttributes().get("time");

        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            event.setEventDate(sdf.parse(date));
        } catch (ParseException ex) {
            Logger.getLogger(EventResource.class.getName()).log(Level.SEVERE, "ParseException", ex);
        }
        event.setEventTime(time);
        eventService.createOrEditEvent(event);

        setStatus(Status.SUCCESS_CREATED);
        return new StringRepresentation("Event created", MediaType.TEXT_PLAIN);
    }
}
