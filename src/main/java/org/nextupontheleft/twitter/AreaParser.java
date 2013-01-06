/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import org.nextupontheleft.mongo.MongoCache;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author john
 */
public class AreaParser {

    private MongoCache dao;
    
    private Set<String> areas = new HashSet<String>();

    public void loadAreas() {
        areas.add("CF");
        areas.add("N");
        areas.add("S");
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
