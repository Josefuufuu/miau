package com.example.miau.service;

import com.example.miau.domain.Role;
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
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    @DisplayName("Crear rol asigna permisos y genera identificador")
    void createRole_withPermissions() {
        Role created = roleService.createRole(new Role("QA_LEAD", "Responsable de calidad"), Set.of(1L, 4L));

        assertNotNull(created.getId());
        assertEquals(2, created.getPermissions().size());
    }

    @Test
    @DisplayName("Crear rol sin permisos lanza excepción")
    void createRole_withoutPermissionsThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> roleService.createRole(new Role("EMPTY_ROLE", "Sin permisos"), Set.of()));

        assertEquals("A role must have at least one permission", exception.getMessage());
    }

    @Test
    @DisplayName("Actualizar rol reemplaza los permisos asociados")
    void updateRole_changesPermissions() {
        Role created = roleService.createRole(new Role("TEAM_LEAD", "Lider de equipo"), Set.of(1L, 3L));

        Role updates = new Role("TEAM_LEAD", "Lider y gestor de proyectos");
        Role updated = roleService.updateRole(created.getId(), updates, Set.of(2L, 3L));

        assertEquals("Lider y gestor de proyectos", updated.getDescription());
        assertEquals(2, updated.getPermissions().size());
        assertTrue(updated.getPermissions().stream().anyMatch(permission -> permission.getId().equals(2L)));
    }

    @Test
    @DisplayName("Eliminar rol borra el registro")
    void deleteRole_removesEntity() {
        Role created = roleService.createRole(new Role("TEMP_ROLE", "Rol temporal"), Set.of(1L));

        roleService.deleteRole(created.getId());

        assertThrows(EntityNotFoundException.class, () -> roleService.getRole(created.getId()));
    }

    @Test
    @DisplayName("Listar roles devuelve registros con permisos")
    void getAllRoles_returnsData() {
        List<Role> roles = roleService.getAllRoles();
        assertFalse(roles.isEmpty());
        assertTrue(roles.stream().anyMatch(role -> !role.getPermissions().isEmpty()));
    }
}
