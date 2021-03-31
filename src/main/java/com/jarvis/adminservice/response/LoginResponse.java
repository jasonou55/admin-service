package com.jarvis.adminservice.response;

import com.jarvis.adminservice.entity.Navigation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class LoginResponse extends GenericResponseImpl {

    private String token;
    private Set<String> roles;

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(final Set<String> roles) {
        this.roles = roles;
    }
}
