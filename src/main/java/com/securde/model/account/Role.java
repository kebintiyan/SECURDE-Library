package com.securde.model.account;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by kevin on 6/26/2017.
 */
public enum Role implements GrantedAuthority {
    STUDENT,
    FACULTY,
    MANAGER,
    STAFF,
    ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
