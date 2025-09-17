package edu.icesi.pensamiento_computacional.services;

import java.util.List;

import edu.icesi.pensamiento_computacional.model.Role;

/**
 * Service contract for managing {@link Role} entities while ensuring they hold
 * at least one permission association.
 */
public interface RoleService {

    /**
     * Persists a new role definition.
     *
     * @param role role data to persist
     * @return the stored entity
     * @throws IllegalArgumentException if the role does not declare at least one permission
     */
    Role createRole(Role role);

    /**
     * Updates an existing role definition.
     *
     * @param id   identifier of the role to update
     * @param role the new state to apply
     * @return the updated entity
     * @throws jakarta.persistence.EntityNotFoundException if the role does not exist
     * @throws IllegalArgumentException                   if the role does not declare at least one permission
     */
    Role updateRole(Integer id, Role role);

    /**
     * Deletes a role by its identifier.
     *
     * @param id identifier of the role to delete
     * @throws jakarta.persistence.EntityNotFoundException if the role does not exist
     */
    void deleteRole(Integer id);

    /**
     * Obtains a role by its identifier.
     *
     * @param id identifier of the role
     * @return the found entity
     * @throws jakarta.persistence.EntityNotFoundException if the role does not exist
     */
    Role getRoleById(Integer id);

    /**
     * Lists all stored roles.
     *
     * @return list of roles
     */
    List<Role> getAllRoles();
}
