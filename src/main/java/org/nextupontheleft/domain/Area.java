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

/**
 *
 * @author john
 */
public class Area {
    
    public static Area ALL = new Area("ALL", "ALL", null);

    @Id
    private String id;
    private String name;
    @Reference
    private Region region;

    @PostPersist
    private void postPersist() {
        try {
            MongoDB db = MongoDB.getInstance();
            db.save(region);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Area() {}
    
    public Area(String id) {
        this.id = id;
    }
    
    public Area(String id, String name, Region region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Area other = (Area) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
    
    
    
}
