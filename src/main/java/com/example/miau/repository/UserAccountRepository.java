package com.example.miau.repository;

import com.example.miau.domain.UserAccount;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<UserAccount> findByUsername(String username);

    @Override
    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    List<UserAccount> findAll();

    @Override
    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<UserAccount> findById(Long id);

    boolean existsByEmail(String email);
}
