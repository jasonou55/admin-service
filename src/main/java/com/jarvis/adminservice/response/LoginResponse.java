package com.jarvis.adminservice.response;

import com.jarvis.adminservice.entity.Navigation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginResponse extends GenericResponseImpl {

    private String token;
    private List<Navigation> navigation;

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public List<Navigation> getNavigation() {
        return navigation;
    }

    public void setNavigation(final List<Navigation> navigation) {
        this.navigation = navigation;
    }
}
