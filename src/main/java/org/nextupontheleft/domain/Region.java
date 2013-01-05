/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.domain;

import com.google.code.morphia.annotations.Id;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 *
 * @author john
 */
public class Region {
    
    public static Region UK = new Region("UK", "United Kingdom");

    @Id
    private String id;
    private String name;
    
    public Region() {}
    
    public Region(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
}
