package com.jarvis.adminservice.service;

import com.jarvis.adminservice.entity.User;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.repository.GenericRepository;
import com.jarvis.adminservice.repository.UserRepository;
import com.jarvis.adminservice.repository.UserRoleRepository;
import com.jarvis.adminservice.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends GenericService<User, UserRequest> implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = this.userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new ServiceException(ErrorCode.NOT_FOUND, "Cannot find the user.", HttpStatus.NOT_FOUND);
    }

    @Override
    public User entity() {
        return new User();
    }

    @Override
    public GenericRepository<User> repository() {
        return userRepository;
    }

//    public boolean assignRolesToUser(String userIdentifier, List<String> roles) {
//        List<Long> roleIds = new ArrayList<>();
//        for (String roleIdentifier : roles) {
//            Optional<Long> roleId = IdentifierGenerator.getIdFromIdentifier(roleIdentifier);
//            if (roleId.isPresent()) {
//                roleIds.add(roleId.get());
//            }
//        }
//        Optional<Long> userId = IdentifierGenerator.getIdFromIdentifier(userIdentifier);
//        if (userId.isPresent()) {
//            Optional<List<UserRole>> userRoles = userRoleRepository.findByUserId(userId.get());
//            if (userRoles.isPresent()) {
//                for (UserRole userRole : userRoles.get()) {
//
//                }
//            }
//        }
//
//    }
}
