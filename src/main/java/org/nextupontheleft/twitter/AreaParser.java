/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import org.nextupontheleft.domain.Area;
import org.nextupontheleft.mongo.NuotlCache;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author john
 */
public class AreaParser {

    private NuotlCache dao;
    
    private Set<String> areas = new HashSet<String>();

    public AreaParser(NuotlCache dao) {
        this.dao = dao;
    }

    public void loadAreas() {
        for(Area area : this.dao.getAreas()) {
            this.areas.add(area.getId().toUpperCase());
        }
    }

    public void addArea(String id) {
        areas.add(id.toUpperCase());
    }
    
    public String parse(String id) throws TweetParsingException {
        String upperId = id.toUpperCase();
        if(!areas.contains(upperId)) {
            throw new TweetParsingException(TweetParsingErrorCode.POSTAL_AREA_ERROR);
        }
        return upperId;
    }
}
