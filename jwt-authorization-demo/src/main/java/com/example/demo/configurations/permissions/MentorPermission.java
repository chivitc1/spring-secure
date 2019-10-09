package com.example.demo.configurations.permissions;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@userAuthorityService.isMentorOfUser(#userId)")
public @interface MentorPermission {
}
