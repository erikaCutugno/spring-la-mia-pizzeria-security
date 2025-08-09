package org.lessons.java.spring_la_mia_pizzeria_crud.Security;

import java.util.HashSet;
import java.util.Set;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Role;
import org.lessons.java.spring_la_mia_pizzeria_crud.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails {
    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<GrantedAuthority>();

        for (Role role : user.getRoles()) {
            this.authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    public Integer getId() {
        return id;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }
      
    public String getPassword() {
        return password;
    }
  
    public String getUsername() {
        return username;
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
