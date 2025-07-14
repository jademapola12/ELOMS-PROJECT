package com.mini.project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private final Employee employee;
    public UserPrincipal(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
        //return List.of();
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
        //return "";
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
        //return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // assume account is not expired for now
        //return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // assume account is not locked for now
        //return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // assume credentials are not expired for now
        // return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true; // assume account is enabled for now
        //return UserDetails.super.isEnabled();
    }
}
