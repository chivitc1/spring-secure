package com.example.demo.configurations;

import com.example.demo.models.SystemRoleType;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username/password."));

        return new CustomUserDetails(user);
    }

    private final class CustomUserDetails extends User implements UserDetails {
        private Set<SystemRoleType> roles;

        CustomUserDetails(User user) {
            setId(user.getId());
            setEmail(user.getEmail());
            setPassword(user.getPassword());
            setRoles(user.getRoles().stream().map(it -> it.name()).collect(Collectors.toSet()));
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return SecurityUtils.toAuthorities(roles);
        }

        @Override
        public String getUsername() {
            return getEmail();
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
}
