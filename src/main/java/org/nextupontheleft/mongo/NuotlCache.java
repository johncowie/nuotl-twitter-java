/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.mongo;

import org.nextupontheleft.domain.*;

import java.util.List;

/**
 *
 * @author john
 */
public interface NuotlCache {
    
    public List<Event> getEvents(int month, int year);
    
    public List<Area> getAreas();
    
    public List<Region> getRegions();
    
    public List<Tweeter> getAuthorisedTweeters();
    
    public void addEvent(Event event);
    
    public void addFailedEvent(FailedEvent event);
    
    public void addEventResponse(EventResponse response);
    
    public Tweeter getTweeter(long id);

    public long getMaxTweetId();
    
}
