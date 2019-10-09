package com.example.demo.configurations;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        for (String uri : AuthorityConstants.PUBLIC_URIs) {
            if (request.getRequestURI() != null && request.getRequestURI().startsWith(uri)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String header = request.getHeader(HEADER_STRING);
        String username = null;
        String authToken = getBearToken(header);
        if (authToken == null) {
            throw new AccessDeniedException("Access is denied");
        }

        username = jwtTokenProvider.getUsernameFromJWT(authToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = (User) userDetailsService.loadUserByUsername(username);

            if (jwtTokenProvider.validateToken(authToken, user)) {
                setUserContext(request, user);
            } else {
                throw new AccessDeniedException("Access is denied");
            }
        }

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, token");
        filterChain.doFilter(request, response);
    }

    private void setUserContext(HttpServletRequest request, User user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getBearToken(String header) {
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            return header.replace(TOKEN_PREFIX,"");

        }
        return null;
    }
}
