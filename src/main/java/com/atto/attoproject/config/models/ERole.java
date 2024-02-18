package com.atto.attoproject.config.models;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
    ROLE_USER("ROLE_USER", "사용자"),
    ROLE_ADMIN("ROLE_ADMIN", "관리자");
    private final String authority;
    private final String descr;

    ERole(String authority, String descr) {
        this.authority = authority;
        this.descr = descr;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public String getDescr() {
        return descr;
    }
}
