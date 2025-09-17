package com.example.miau.service;

import com.example.miau.domain.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role createRole(Role role, Set<Long> permissionIds);

    Role updateRole(Long id, Role role, Set<Long> permissionIds);

    void deleteRole(Long id);

    Role getRole(Long id);

    List<Role> getAllRoles();
}
