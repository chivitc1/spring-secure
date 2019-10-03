package com.example.demo.configurations;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public class CustomUserAuthenticationProvider implements AuthenticationProvider {

    public static final String INVALID_USERNAME_PASSWORD_MSG = "Invalid username/password";
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomUserAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String email = token.getName();
        User user = userService.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException(INVALID_USERNAME_PASSWORD_MSG));
        String password = user.getPassword();
        boolean passwordMatch = passwordEncoder.matches(token.getCredentials().toString(), password);
        if(!passwordMatch) {
            throw new BadCredentialsException(INVALID_USERNAME_PASSWORD_MSG);
        }
//        UserContext userContext = new UserContext();
//        userContext.setUserId(user.getId());
//        UserContextHolder.setContext(userContext);

        Collection<? extends GrantedAuthority> authorities = SecurityUtils.toAuthorities(user.getRoles());
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, password, authorities);

        System.out.println("Set Security context");
        System.out.println(result.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
