package com.example.demo.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthToken {
    private String accessToken;

    public AuthToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
