/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Mima
 */
@Entity
@Table(name = "Tag")
public class Tag extends EntityGenericBase<Integer> {
    
    @Column(name = "tag")
    private String tag;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<Event> events;
    
    public Tag() {
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the events
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
