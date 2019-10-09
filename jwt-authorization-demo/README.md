# Spring security
- Secure api using JWT and OAuth2

- WebSecurityConfig: customize AuthenticationProvider, UserDetailsService, common security config

- AuthorizationServerConfig: config oauth2 endpoint using JWT, oauth2 client config

- ResourceServerConfig: common security config for api, but it currently has less config due to annotation secure config at api method level 

- JwtTokenConfig: config oauth2 JWT support

- Get JWT Oauth2 token: 

POST /auth/oauth2/token 

form-data payload:
```
grant_type:password
scope:webClient
username:user1@example.com
password:123456
client_id:webAppClient
client_secret:123456
```

- Request api with bearer token in Authorization header

GET /api/users

headers:
```
Authorization: Bearer {{token}}
```