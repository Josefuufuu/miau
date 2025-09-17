package com.example.miau.service.impl;

import com.example.miau.domain.Role;
import com.example.miau.domain.UserAccount;
import com.example.miau.repository.RoleRepository;
import com.example.miau.repository.UserAccountRepository;
import com.example.miau.service.UserAccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, RoleRepository roleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserAccount createUser(UserAccount userAccount, Set<Long> roleIds) {
        validateRoleIds(roleIds);
        userAccountRepository.findByUsername(userAccount.getUsername())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Username already exists");
                });
        if (userAccountRepository.existsByEmail(userAccount.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        userAccount.setRoles(loadRoles(roleIds));
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount updateUser(Long id, UserAccount userAccount, Set<Long> roleIds) {
        validateRoleIds(roleIds);
        UserAccount existing = userAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!existing.getUsername().equals(userAccount.getUsername())) {
            userAccountRepository.findByUsername(userAccount.getUsername())
                    .ifPresent(conflict -> {
                        throw new IllegalArgumentException("Username already exists");
                    });
        }
        if (!existing.getEmail().equals(userAccount.getEmail()) && userAccountRepository.existsByEmail(userAccount.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        existing.setUsername(userAccount.getUsername());
        existing.setEmail(userAccount.getEmail());
        existing.setPassword(userAccount.getPassword());
        existing.setRoles(loadRoles(roleIds));
        return userAccountRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userAccountRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userAccountRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getUser(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    private void validateRoleIds(Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            throw new IllegalArgumentException("A user must have at least one role");
        }
    }

    private Set<Role> loadRoles(Set<Long> roleIds) {
        List<Role> roles = roleRepository.findAllById(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new EntityNotFoundException("One or more roles were not found");
        }
        return new HashSet<>(roles);
    }
}
