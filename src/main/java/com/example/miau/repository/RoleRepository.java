package com.example.miau.repository;

import com.example.miau.domain.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @EntityGraph(attributePaths = "permissions")
    Optional<Role> findByName(String name);

    @Override
    @EntityGraph(attributePaths = "permissions")
    List<Role> findAll();

    @Override
    @EntityGraph(attributePaths = "permissions")
    Optional<Role> findById(Long id);
}
