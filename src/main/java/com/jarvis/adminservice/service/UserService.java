package com.jarvis.adminservice.service;

import com.jarvis.adminservice.entity.User;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = this.userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new ServiceException(ErrorCode.NOT_FOUND, "Cannot find the user.", HttpStatus.NOT_FOUND);
    }
}
