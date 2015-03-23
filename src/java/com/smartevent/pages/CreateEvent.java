/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.pages;

import com.smartevent.data.Role;
import com.smartevent.entities.Event;
import com.smartevent.services.business.EventService;
import com.smartevent.services.security.ProtectedPage;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 *
 * @author Mima
 */
@Import(library = "context:js/Notifier.js")
@ProtectedPage(role=Role.USER)
public class CreateEvent {
    
    @Inject
    private JavaScriptSupport javaScriptSupport;
    
    void afterRender() {
        JSONObject json = new JSONObject();
        json.put("locale", "english");
        json.put("key", "create_new_event_page_loaded");
        javaScriptSupport.addInitializerCall("notify", json);
    }
    
    void cleanupRender() {
        JSONObject json2 = new JSONObject();
        json2.put("locale", "srpski");
        json2.put("key", "hello_world");
        javaScriptSupport.addInitializerCall("get", json2);
    }
    
    @Inject
    private EventService eventService;
    
    @Persist("flash")
    private String eventId;
    
    @InjectPage
    private ViewEvents viewEvents;
    @Property
    private Event event = new Event();
    @Inject
    private Messages messages;
    
    void onPrepare() throws Exception {
        if(eventId != null) {
            this.event = eventService.getEventById(eventId);
        } else {
            event = new Event();
        }
    }
    
    @CommitAfter
    public Object onActionFromCreateEvent(){
        System.out.println("Create Event Action");
        eventService.createOrEditEvent(event);
        viewEvents.eventTitle(event.getTitle());
        event = null;
        eventId = null;
        return viewEvents;
    }

    public void editEvent(String eventId) {
        this.eventId = eventId;
    }
    
    public String getSubmitActionLabel () {
        return event != null && event.getId() != null ? messages.get("action_edit") : messages.get("page_contact_form_button_create_label");
    }
}
