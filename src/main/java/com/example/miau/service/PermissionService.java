package com.example.miau.service;

import com.example.miau.domain.Permission;

import java.util.List;

public interface PermissionService {

    Permission createPermission(Permission permission);

    Permission updatePermission(Long id, Permission updatedPermission);

    void deletePermission(Long id);

    Permission getPermission(Long id);

    List<Permission> getAllPermissions();
}
