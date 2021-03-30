package com.jarvis.adminservice.service;

import com.jarvis.adminservice.entity.User;
import com.jarvis.adminservice.entity.UserRole;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.repository.GenericRepository;
import com.jarvis.adminservice.repository.UserRepository;
import com.jarvis.adminservice.repository.UserRoleRepository;
import com.jarvis.adminservice.request.UserRequest;
import com.jarvis.adminservice.util.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

import java.util.ArrayList;
import java.util.List;
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

    public void assignRolesToUser(String userIdentifier, List<String> roleIdentifiers) {
        Optional<Long> userIdOptional = IdentifierGenerator.getIdFromIdentifier(userIdentifier);
        if (!userIdOptional.isPresent()) {
            throw new ServiceException(
                    ErrorCode.NOT_FOUND,
                    "Cannot find user by " + userIdentifier,
                    HttpStatus.NOT_FOUND);
        }

        long userId = userIdOptional.get();
        List<Long> assignRoleIds = new ArrayList<>();
        for (String roleIdentifier : roleIdentifiers) {
            Optional<Long> roleId = IdentifierGenerator.getIdFromIdentifier(roleIdentifier);
            roleId.ifPresent(assignRoleIds::add);
        }

        Optional<List<UserRole>> assignedUserRolesOptional = userRoleRepository.findAllByUserId(userId);
        if (assignedUserRolesOptional.isPresent()) {
            List<UserRole> assignedUserRoles = assignedUserRolesOptional.get();
            for (UserRole userRole : assignedUserRoles) {
                if (!assignRoleIds.contains(userRole.getRoleId())) {
                    userRole.setEnabled(false);
                    userRoleRepository.save(userRole);
                }
                if (!userRole.isEnabled()) {
                    userRole.setEnabled(true);
                    assignRoleIds.remove(userRole.getUserId());
                }
                userRoleRepository.save(userRole);
            }
        }
        for (long roleId : assignRoleIds) {

            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleRepository.save(userRole);
        }
    }
}
