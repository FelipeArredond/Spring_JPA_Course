package com.platzi.pizza.application;

import com.platzi.pizza.persistence.entities.UserEntity;
import com.platzi.pizza.persistence.entities.UserRoleEntity;
import com.platzi.pizza.persistence.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User" + username + " not found"));
        String[] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);
        return User
                .builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(this.grantedAuthorityList(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }

    private String[] getAuthorities(String rol){
        if ("ADMIN".equals(rol) || "CUSTOMER".equals(rol)){
            return new String[] {"random_order"};
        }
        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorityList(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for(String rol: roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol));
            for (String authority: this.getAuthorities(rol)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }
}
