package com.example.miau.service.impl;

import com.example.miau.domain.Permission;
import com.example.miau.repository.PermissionRepository;
import com.example.miau.service.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        permissionRepository.findByName(permission.getName())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Permission name already exists");
                });
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission updatedPermission) {
        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));

        if (!existing.getName().equals(updatedPermission.getName())) {
            permissionRepository.findByName(updatedPermission.getName())
                    .ifPresent(conflict -> {
                        throw new IllegalArgumentException("Permission name already exists");
                    });
        }

        existing.setName(updatedPermission.getName());
        existing.setDescription(updatedPermission.getDescription());
        return permissionRepository.save(existing);
    }

    @Override
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new EntityNotFoundException("Permission not found");
        }
        permissionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Permission getPermission(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
