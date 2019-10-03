# spring security framework

## Keywords
Web Servlet filter; Request/Response Filter chain; interceptor; Authentication Manager; AccessDecission Manager; Authentication; Authorization; Identity; Who a user is; What a user can do with app; Make sure caller is who they claim to be;
Data Store; Validate credentials; Validate access; Secured Resource;
ProviderManager; AuthenticationProvider; PasswordEncoder; UserDetailsService; UserDetails; User model;
AuthenticationFilter; SecurityContext; Authentication object; Principal and Authorities;

## Authentication architecture
request -> AuthenticationFilter -> AuthenticationManager -> AuthenticationProvider

AuthenticationFilter -> SecurityContext -> Authentication: Principal + Authorities

UsernamePasswordAuthenticationFilter -> extract username, password -> UsernamePasswordAuthenticationToken -> AuthenticationManager.authen() -> Authentication object, AuthenticationException, Null

AuthenticationManager -> ProviderManager

AuthenticationProvider -> PasswordEncoder

AuthenticationProvider -> UserDetailsService -> UserDetails -> User Model

UserDetailsService -> loadUserByUsername()

AuthenticationProvider: Dao, Jaas, Caas, X509, LDAP
