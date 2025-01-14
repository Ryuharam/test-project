package com.ssafy.userservice.jwt;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class UserPrincipal implements UserDetails {

    private User user;
    private Collection<? extends GrantedAuthority> authorities;

//    public UserPrincipal(User user) {
//        this.user = user;
//        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getKey()));
//    }
//
//    public UserPrincipal(User user, Map<String, Object> attributes, String nameAttributeKey) {
//        this.user = user;
//        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getKey()));
//    }

    /**
     * UserDetails method implements
     */
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return String.valueOf(user.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
