package com.example.miau.service;

import com.example.miau.domain.Permission;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    @DisplayName("Crear permiso persiste y asigna identificador")
    void createPermission_persistsEntity() {
        Permission created = permissionService.createPermission(new Permission("TASK_VIEW", "Permite ver tareas"));

        assertNotNull(created.getId());
        Permission fetched = permissionService.getPermission(created.getId());
        assertEquals("TASK_VIEW", fetched.getName());
        assertEquals("Permite ver tareas", fetched.getDescription());
    }

    @Test
    @DisplayName("Actualizar permiso modifica sus valores")
    void updatePermission_updatesFields() {
        Permission created = permissionService.createPermission(new Permission("TASK_EDIT", "Permite editar tareas"));
        Permission changes = new Permission("TASK_UPDATE", "Actualizar tareas existentes");

        Permission updated = permissionService.updatePermission(created.getId(), changes);
        assertEquals("TASK_UPDATE", updated.getName());
        assertEquals("Actualizar tareas existentes", updated.getDescription());
    }

    @Test
    @DisplayName("Eliminar permiso elimina el registro")
    void deletePermission_removesEntity() {
        Permission created = permissionService.createPermission(new Permission("TASK_DELETE", "Eliminar tareas"));

        permissionService.deletePermission(created.getId());

        assertThrows(EntityNotFoundException.class, () -> permissionService.getPermission(created.getId()));
    }

    @Test
    @DisplayName("Listar permisos recupera al menos los iniciales")
    void getAllPermissions_returnsList() {
        List<Permission> permissions = permissionService.getAllPermissions();
        assertFalse(permissions.isEmpty());
    }
}
