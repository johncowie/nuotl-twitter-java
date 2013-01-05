/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import org.nextupontheleft.domain.Area;
import org.nextupontheleft.mongo.MongoCache;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author john
 */
public class AreaParser {

    private MongoCache dao;
    
    private Map<String, Area> areas = new TreeMap<String, Area>();
    
    @PostConstruct
    private void loadAreas() {
        for(Area area : dao.getAreas()) {
            addArea(area);
        }
    }

    public void addArea(Area area) {
        areas.put(area.getId(), area);
    }
    
    public Area parse(String id) throws TweetParsingException {
        Area area = areas.get(id);
        if(area == null) {
            throw new TweetParsingException(TweetParsingErrorCode.POSTAL_AREA_ERROR);
        }
        return area;
    }
}
