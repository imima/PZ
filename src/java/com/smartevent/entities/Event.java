/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.Validate;

/**
 *
 * @author Mima
 */
@Entity
@Table(name = "Event")
public class Event extends EntityGenericBase<Integer> {

    @Validate("required")
    @Column(name = "title")
    private String title;
    
    @Validate("required")
    @Column(name = "description")
    private String description;
    
    @Validate("required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_date")
    private Date eventDate;
    
    @Validate("required")
    @Column(name = "event_time")
    private String eventTime;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Event_Location", 
            joinColumns = { @JoinColumn(name = "Event_id", nullable = false, updatable = false) }, 
            inverseJoinColumns = { @JoinColumn(name = "Location_id", nullable = false, updatable = false) })
    private Set<Location> locations;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Event_Tag", 
            joinColumns = { @JoinColumn(name = "Event_id", nullable = false, updatable = false) }, 
            inverseJoinColumns = { @JoinColumn(name = "Tag_id", nullable = false, updatable = false) })
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name ="User_id", nullable = true)
    private User user;
    
    /**
     * Empty constructor
     */
    public Event() {
    }

    public Event(String title, String description, Date date, String time) {
        this.title = title;
        this.description = description;
        this.eventDate = date;
        this.eventTime = time;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @return the date
     */
    public String getDateFormated() {
        return "";//new SimpleDateFormat("dd/mm/yyyy").format(eventDate.getTime());
    }
    
    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the eventTime
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * @param eventTime the eventTime to set
     */
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    
    /**
     * @return the locations
     */
    public Set<Location> getLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    /**
     * @return the tags
     */
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    
}
