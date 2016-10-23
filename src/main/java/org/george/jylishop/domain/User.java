package org.george.jylishop.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 23.10.2016.
 */
public class User {
    private String username;
    private String password;
    private boolean enabled;
    private List<String> roles = new ArrayList<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}