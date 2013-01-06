/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Property;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 *
 * @author john
 */
@Entity(value="tweeter", noClassnameStored=true)
public class Tweeter {
    @Id
    private long id;
	private String name;
    @Property("display-name")
    private String display_name;
	private Approved approved;
	
	public Tweeter() {}
    
	public Tweeter(long id, String name, String display_name, Approved approved) {
		this.name = name;
		this.id = id;
		this.approved = approved;
        this.display_name = display_name;
	}
	
	public String getName() {
		return this.name;
	}
    
    public String getDisplay_name() {
        return this.display_name;
    }
	
	public long getId() {
		return this.id;
	}
	
	public Approved getApproved() {
		return this.approved;
	}

    public void setApproved(Approved approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
