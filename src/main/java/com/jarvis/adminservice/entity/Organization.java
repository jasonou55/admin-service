package com.jarvis.adminservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "organizations")
public class Organization extends GenericEntityImpl {

    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
