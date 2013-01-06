/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Property;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 *
 * @author john
 */
@Entity(value="event", noClassnameStored=true)
public class Event {

    @Id
    private long id;
    @Property("start")
    private Date start_time;
    @Property("end")
    private Date end_time;
    private String area;
    @Property("text")
    private String html;
    private String tags;
    private long tweeter;
    private Approved approved;

    public Event() {
    }

    public Event(long id, Date start_time, Date end_time, String area, String html, String tags, long tweeter, Approved approved) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.area = area;
        this.html = html;
        this.tags = tags;
        this.tweeter = tweeter;
        this.approved = approved;
    }
    
    public long getTweeter() {
        return this.tweeter;
    }

    public Approved getApproved() {
        return approved;
    }

    public String getArea() {
        return area;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public String getHtml() {
        return html;
    }

    public long getId() {
        return id;
    }

    public Date getStart_time() {
        return start_time;
    }
    
    public String getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
