/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.domain;

import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.PostPersist;
import com.google.code.morphia.annotations.Reference;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.nextupontheleft.mongo.MongoDB;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author john
 */
public class Event {

    @Id
    private long id;
    private Date start_time;
    private Date end_time;
    @Reference
    private Area area;
    private String html;
    private String tags;
    @Reference
    private Tweeter tweeter;
    private Approved approved;

    @PostPersist
    private void postPersist() {
        try {
            MongoDB db = MongoDB.getInstance();
            db.save(area);
            db.save(tweeter);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Event() {
    }

    public Event(long id, Date start_time, Date end_time, Area area, String html, String tags, Tweeter tweeter, Approved approved) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.area = area;
        this.html = html;
        this.tags = tags;
        this.tweeter = tweeter;
        this.approved = approved;
    }
    
    public Tweeter getTweeter() {
        return this.tweeter;
    }

    public Approved getApproved() {
        return approved;
    }

    public Area getArea() {
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

    public String getStart_timeFormatted() {
        if(start_time == null) {
            return "-";
        } else {
            return new SimpleDateFormat("HH:mm").format(start_time);
        }
    }
    
    public String getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
