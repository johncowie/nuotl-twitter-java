/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 *
 * @author john
 */
@Entity(value="event", noClassnameStored=true)
public class EventResponse {
    @Id
    private long id;
    private long originator;
    private String text;
    
    public EventResponse() {}
    
    public EventResponse(long id, long originator, String text) {
        this.id = id;
        this.originator = originator;
        this.text = text;
    }
}
