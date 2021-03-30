package com.jarvis.adminservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.util.IdentifierGenerator;
import org.springframework.http.HttpStatus;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import java.util.Optional;

@MappedSuperclass
public class GenericEntityImpl implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    protected long id;

    @Column(nullable = false)
    protected long created;

    @Column(nullable = false)
    protected long createdBy;

    @Column(nullable = false)
    protected boolean enabled;

    @Transient
    protected String identifier;

    public GenericEntityImpl() {
        this.enabled = true;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(final long id) {
        this.id = id;
    }
    @Override
    public long getCreated() {
        return created;
    }

    @Override
    public void setCreated(final long created) {
        this.created = created;
    }

    @Override
    public long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(final long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getIdentifier() {
        if (identifier == null) {
            this.setIdentifier();
        }
        return identifier;
    }

    @Override
    public void setIdentifier() {

        Optional<String> identifierOptional = IdentifierGenerator.getIdentifierFromId(this.id, this.created);
        if (!identifierOptional.isPresent()) {
            throw new ServiceException(
                    ErrorCode.GENERATE_IDENTIFIER_ERROR,
                    ErrorCode.GENERATE_IDENTIFIER_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        this.identifier = identifierOptional.get();
    }
}
