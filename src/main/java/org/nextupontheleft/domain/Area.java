package org.nextupontheleft.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value="area", noClassnameStored=true)
public class Area {
    @Id
    private String id;
    private String name;
    private String region;

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.id;
    }

    public String getRegion() {
        return this.region;
    }
}
