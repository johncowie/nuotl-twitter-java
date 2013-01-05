/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.domain;

/**
 *
 * @author john
 */
public class FailedEvent {
    private long id;
    private String text;
    private Tweeter tweeter;
    
    public FailedEvent() {}
    
    public FailedEvent(long id, String text, Tweeter tweeter){
        this.id = id;
        this.text = text;
        this.tweeter = tweeter;
    }
    
    public Tweeter getTweeter() {
        return this.tweeter;
    }
}
