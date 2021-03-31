package com.jarvis.adminservice.service;

import com.jarvis.adminservice.entity.Navigation;
import com.jarvis.adminservice.repository.GenericRepository;
import com.jarvis.adminservice.repository.NavigationRepository;
import com.jarvis.adminservice.request.NavigationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NavigationService extends GenericService<Navigation, NavigationRequest>{

    @Autowired
    private NavigationRepository navigationRepository;

    @Override public Navigation entity() {
        return new Navigation();
    }

    @Override public GenericRepository<Navigation> repository() {
        return navigationRepository;
    }
}
