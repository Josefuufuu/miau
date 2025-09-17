package edu.icesi.pensamiento_computacional.services;

import java.util.List;

import edu.icesi.pensamiento_computacional.model.Permission;

/**
 * Service contract for managing {@link Permission} entities.
 */
public interface PermissionService {

    /**
     * Persists a new permission definition.
     *
     * @param permission permission data to persist
     * @return the stored entity
     */
    Permission createPermission(Permission permission);

    /**
     * Updates an existing permission definition.
     *
     * @param id         identifier of the permission to update
     * @param permission the new data to apply
     * @return the updated entity
     * @throws jakarta.persistence.EntityNotFoundException if the permission does not exist
     */
    Permission updatePermission(Integer id, Permission permission);

    /**
     * Removes a permission definition.
     *
     * @param id identifier of the permission to delete
     * @throws jakarta.persistence.EntityNotFoundException if the permission does not exist
     */
    void deletePermission(Integer id);

    /**
     * Retrieves a permission by its identifier.
     *
     * @param id identifier of the permission
     * @return the found entity
     * @throws jakarta.persistence.EntityNotFoundException if the permission does not exist
     */
    Permission getPermissionById(Integer id);

    /**
     * Lists all stored permissions.
     *
     * @return list of permissions
     */
    List<Permission> getAllPermissions();
}
