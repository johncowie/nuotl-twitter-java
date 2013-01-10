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

    public void addEvent(Event event);

    public void addTweeter(Tweeter tweeter);
    
    public Tweeter getTweeter(long id);

    public void addFeature(FeatureTweet feature);

    public List<Area> getAreas();
    
}
