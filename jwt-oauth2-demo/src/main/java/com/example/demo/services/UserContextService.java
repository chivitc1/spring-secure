package com.example.demo.services;

import com.example.demo.models.User;

public interface UserContextService {
    User getCurrentUser();
    void setCurrentUser(final User user);
}
