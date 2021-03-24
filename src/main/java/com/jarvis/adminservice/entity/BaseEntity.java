package com.jarvis.adminservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarvis.adminservice.util.IdentifierGenerator;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Repository
@javax.persistence.Entity
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonIgnore
    private long id;

    @Column(nullable = false)
    private long created;

    @Column(nullable = false)
    private long createdBy;

    @Column(nullable = false)
    private boolean active;

    @Transient
    private String identifier;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(final long created) {
        this.created = created;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final long createdBy) {
        this.createdBy = createdBy;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getIdentifier() {
        if (identifier == null) {
            this.setIdentifier();
        }
        return identifier;
    }

    public void setIdentifier() {
        this.identifier = IdentifierGenerator.getIdentifierFromId(this.id, this.created).get();
    }
}
