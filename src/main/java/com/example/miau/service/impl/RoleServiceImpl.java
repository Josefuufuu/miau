package com.example.miau.service.impl;

import com.example.miau.domain.Permission;
import com.example.miau.domain.Role;
import com.example.miau.repository.PermissionRepository;
import com.example.miau.repository.RoleRepository;
import com.example.miau.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Role createRole(Role role, Set<Long> permissionIds) {
        validatePermissionIds(permissionIds);
        roleRepository.findByName(role.getName())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Role name already exists");
                });

        role.setPermissions(loadPermissions(permissionIds));
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role role, Set<Long> permissionIds) {
        validatePermissionIds(permissionIds);
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        if (!existing.getName().equals(role.getName())) {
            roleRepository.findByName(role.getName())
                    .ifPresent(conflict -> {
                        throw new IllegalArgumentException("Role name already exists");
                    });
        }

        existing.setName(role.getName());
        existing.setDescription(role.getDescription());
        existing.setPermissions(loadPermissions(permissionIds));
        return roleRepository.save(existing);
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found");
        }
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRole(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    private void validatePermissionIds(Set<Long> permissionIds) {
        if (permissionIds == null || permissionIds.isEmpty()) {
            throw new IllegalArgumentException("A role must have at least one permission");
        }
    }

    private Set<Permission> loadPermissions(Set<Long> permissionIds) {
        List<Permission> permissions = permissionRepository.findAllById(permissionIds);
        if (permissions.size() != permissionIds.size()) {
            throw new EntityNotFoundException("One or more permissions were not found");
        }
        return new HashSet<>(permissions);
    }
}
