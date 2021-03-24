package com.jarvis.adminservice.service;

import com.jarvis.adminservice.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService {

    AccountService(final BaseRepository baseRepository) {
        super(baseRepository);
    }
}
