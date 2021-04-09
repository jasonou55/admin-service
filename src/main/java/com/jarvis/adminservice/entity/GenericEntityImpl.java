package com.jarvis.adminservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.util.IdentifierGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpStatus;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import java.util.Optional;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class GenericEntityImpl extends Auditable<String> implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    protected long id;

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

        Optional<String> identifierOptional = IdentifierGenerator.getIdentifierFromId(this.id, this.createdDate);
        if (!identifierOptional.isPresent()) {
            throw new ServiceException(
                    ErrorCode.GENERATE_IDENTIFIER_ERROR,
                    ErrorCode.GENERATE_IDENTIFIER_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        this.identifier = identifierOptional.get();
    }
}
