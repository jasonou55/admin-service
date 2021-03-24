package com.jarvis.adminservice.service;

import com.jarvis.adminservice.entity.BaseEntity;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.repository.BaseRepository;
import com.jarvis.adminservice.request.BaseRequest;
import com.jarvis.adminservice.util.IdentifierGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class BaseService {

    private final BaseRepository<BaseEntity> baseRepository;

    BaseService(BaseRepository<BaseEntity> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public BaseEntity getById(String identifier) {
        return getEntityByIdentifier(identifier);
    }

    public BaseEntity create(BaseRequest baseRequest) {
        BaseEntity baseEntity = new BaseEntity();
        BeanUtils.copyProperties(baseRequest, baseEntity);
        return (BaseEntity) baseRepository.save(baseEntity);
    }

    public BaseEntity update(BaseRequest baseRequest) {
        BaseEntity persistEntity = getEntityByIdentifier(baseRequest.getIdentifier());
        BeanUtils.copyProperties(baseRequest, persistEntity);
        return (BaseEntity) baseRepository.save(persistEntity);
    }

    public boolean inActive(BaseRequest baseRequest) {
        BaseEntity persistEntity = getEntityByIdentifier(baseRequest.getIdentifier());
        persistEntity.setActive(false);
        return true;
    }

    public boolean active(BaseRequest baseRequest) {
        BaseEntity persistEntity = getEntityByIdentifier(baseRequest.getIdentifier());
        persistEntity.setActive(true);
        return true;
    }

    private BaseEntity getEntityByIdentifier(String identifier) {
        Optional<Long> idOptional = IdentifierGenerator.getIdFromIdentifier(identifier);
        if (idOptional.isPresent()) {
            long id = idOptional.get();
            Optional<BaseEntity> baseEntityOptional = baseRepository.findById(id);
            if (baseEntityOptional.isPresent()) {
                return baseEntityOptional.get();
            }
        }
        throw new ServiceException(ErrorCode.NOT_FOUND, "Cannot found entity.", HttpStatus.BAD_REQUEST);
    }
}
