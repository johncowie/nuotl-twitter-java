/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.domain;

/**
 *
 * @author john
 */
public class EventResponse {
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
