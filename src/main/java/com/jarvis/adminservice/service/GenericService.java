package com.jarvis.adminservice.service;

import com.jarvis.adminservice.entity.GenericEntity;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.repository.GenericRepository;
import com.jarvis.adminservice.request.GenericRequest;
import com.jarvis.adminservice.util.BeanUtilities;
import com.jarvis.adminservice.util.IdentifierGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<T extends GenericEntity, S extends GenericRequest> {

    public abstract T entity();
    public abstract GenericRepository<T> repository();

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T getOne(String identifier) {
        Optional<Long> idOptional = IdentifierGenerator.getIdFromIdentifier(identifier);
        if (idOptional.isPresent()) {
            long id = idOptional.get();
            Optional<T> baseEntityOptional = this.repository().findById(id);
            if (baseEntityOptional.isPresent()) {
                return baseEntityOptional.get();
            }
        }
        throw new ServiceException(ErrorCode.NOT_FOUND, "Cannot found entity.", HttpStatus.BAD_REQUEST);
    }

    public List<T> getAll() {
        return this.repository().findAllByOrderByIdDesc();
    }

    public List<T> getAllEnabled() {
        return this.repository().findAllByEnabledOrderByIdDesc(true);
    }

    public List<T> getAllDisabled() {
        return this.repository().findAllByEnabledOrderByIdDesc(false);
    }

    @Transactional(rollbackFor = Exception.class)
    public T create(S request) {
        T entity = this.entity();
        BeanUtils.copyProperties(request, entity);
        entity.setCreated(System.currentTimeMillis());
        return this.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public T update(S request, String... ignoreProperties) {
        T entity = this.getOne(request.getIdentifier());
        BeanUtils.copyProperties(request, entity, BeanUtilities.getIgnorePropertyNames(request, ignoreProperties));
        return this.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void enable(String identifier) {
        this.switchState(identifier, true);
    }

    @Transactional(rollbackFor = Exception.class)
    public void enableAll(List<String> identifiers) {
        this.switchStateAll(identifiers, true);
    }

    @Transactional(rollbackFor = Exception.class)
    public void disable(String identifier) {
        this.switchState(identifier, false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void disableAll(List<String> identifiers) {
        this.switchStateAll(identifiers, false);
    }

    public T save(T entity) {
        entity.setUpdated(System.currentTimeMillis());
        return this.repository().save(entity);
    }

    private void switchStateAll(List<String> identifiers, boolean isEnable) {
        if (!identifiers.isEmpty()) {
            for (String identifier : identifiers) {
                this.switchState(identifier, isEnable);
            }
        }
    }

    private void switchState(String identifier, boolean isEnable) {
        T entity = this.getOne(identifier);
        entity.setEnabled(isEnable);
        this.repository().save(entity);
    }
}
