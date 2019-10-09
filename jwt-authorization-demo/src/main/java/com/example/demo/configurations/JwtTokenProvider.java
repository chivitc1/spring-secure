package com.example.demo.configurations;

import com.example.demo.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    private String jwtSecret = "SECRET";

    private int jwtExpirationInSeconds = 5*60;

    public String generateToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();

        AuthorityClaims authorityClaims = new AuthorityClaims();
        authorityClaims.setTeamRoles(userPrincipal.getTeams()
                .stream()
                .map(it -> new AuthorityClaims.TeamRole(it.getTeam().getId(), it.getRole()))
                .collect(Collectors.toList()));

        // set system role
        authorityClaims.setRoles(userPrincipal.getRoles().stream().map(it -> it.name()).collect(Collectors.toSet()));

        Claims claims = Jwts.claims().setSubject(userPrincipal.getEmail());

        claims.put("scopes", authorityClaims);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInSeconds * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            return null;
        }

        return claims.getSubject();
    }

    public Boolean validateToken(String token, User user)
    {
        final String username = getUsernameFromJWT(token);
        return (username != null &&
                username.equals(user.getEmail())
                        && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
