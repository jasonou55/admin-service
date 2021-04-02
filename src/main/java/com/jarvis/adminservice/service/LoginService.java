package com.jarvis.adminservice.service;

import com.jarvis.adminservice.constant.SettingConstants;
import com.jarvis.adminservice.entity.Role;
import com.jarvis.adminservice.entity.User;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import com.jarvis.adminservice.repository.UserRepository;
import com.jarvis.adminservice.request.LoginRequest;
import com.jarvis.adminservice.response.LoginResponse;
import com.jarvis.adminservice.util.EncryptUtils;
import com.jarvis.adminservice.util.JwtTokenUtils;
import com.jarvis.adminservice.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtils redisUtils;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(loginRequest.getUsername(),
                EncryptUtils.md5Encrypt(EncryptUtils.aesDecrypt(loginRequest.getPassword())));
        if (!userOptional.isPresent()) {
            throw new ServiceException(ErrorCode.AUTHENTICATION_FAILED, "Username or password is incorrect.", HttpStatus.BAD_GATEWAY);
        }
        User existUser = userOptional.get();
        LoginResponse loginResponse = new LoginResponse();
        Set<String> roles = new HashSet<>();
        if (existUser.getRoles() != null) {
            for (Role role : existUser.getRoles()) {
                roles.add(role.getName());
            }
        }
        String token = JwtTokenUtils.generate(existUser.getUsername());
        loginResponse.setSuccess(true);
        loginResponse.setToken(token);
        loginResponse.setRoles(roles);
        redisUtils.set(token, String.valueOf(System.currentTimeMillis()), SettingConstants.USER_JWT_EXPIRE_TIME);
        return loginResponse;
    }
}
