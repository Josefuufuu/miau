package com.example.miau.service;

import com.example.miau.domain.UserAccount;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;

    @Test
    @DisplayName("Crear usuario asigna roles obligatorios")
    void createUser_withRoles() {
        UserAccount created = userAccountService.createUser(new UserAccount("tester1", "tester1@example.com", "secret"), Set.of(1L));

        assertNotNull(created.getId());
        assertTrue(created.getRoles().stream().anyMatch(role -> role.getId().equals(1L)));
    }

    @Test
    @DisplayName("Crear usuario sin roles produce error")
    void createUser_withoutRolesThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userAccountService.createUser(new UserAccount("tester2", "tester2@example.com", "secret"), Set.of()));

        assertEquals("A user must have at least one role", exception.getMessage());
    }

    @Test
    @DisplayName("Actualizar usuario modifica datos y roles")
    void updateUser_changesData() {
        UserAccount created = userAccountService.createUser(new UserAccount("tester3", "tester3@example.com", "secret"), Set.of(1L));

        UserAccount updates = new UserAccount("tester3", "tester3.new@example.com", "newpass");
        UserAccount updated = userAccountService.updateUser(created.getId(), updates, Set.of(2L));

        assertEquals("tester3.new@example.com", updated.getEmail());
        assertTrue(updated.getRoles().stream().anyMatch(role -> role.getId().equals(2L)));
    }

    @Test
    @DisplayName("Eliminar usuario borra el registro")
    void deleteUser_removesEntity() {
        UserAccount created = userAccountService.createUser(new UserAccount("tester4", "tester4@example.com", "secret"), Set.of(1L));

        userAccountService.deleteUser(created.getId());

        assertThrows(EntityNotFoundException.class, () -> userAccountService.getUser(created.getId()));
    }

    @Test
    @DisplayName("Listar usuarios devuelve información con roles")
    void getAllUsers_returnsData() {
        List<UserAccount> users = userAccountService.getAllUsers();
        assertFalse(users.isEmpty());
        assertTrue(users.stream().allMatch(user -> !user.getRoles().isEmpty()));
    }
}
