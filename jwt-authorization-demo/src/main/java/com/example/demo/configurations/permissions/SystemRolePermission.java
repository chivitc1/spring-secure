package com.example.demo.configurations.permissions;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('SYSTEM', 'SYSTEM_ADMIN')")
public @interface SystemRolePermission {
}
