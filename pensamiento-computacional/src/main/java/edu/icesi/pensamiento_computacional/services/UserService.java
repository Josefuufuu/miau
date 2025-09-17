package edu.icesi.pensamiento_computacional.services;

import java.util.List;

import edu.icesi.pensamiento_computacional.model.UserAccount;

/**
 * Service contract for managing {@link UserAccount} entities.
 * <p>
 * Implementations must enforce that a user always has at least one role
 * associated when being created or updated.
 * </p>
 */
public interface UserService {

    /**
     * Persists a new user account.
     *
     * @param user the user to persist
     * @return the stored entity
     * @throws IllegalArgumentException if the user does not have at least one role associated
     */
    UserAccount createUser(UserAccount user);

    /**
     * Updates the state of an existing user account.
     *
     * @param id   identifier of the user to update
     * @param user the new data to apply
     * @return the updated entity
     * @throws jakarta.persistence.EntityNotFoundException if the user does not exist
     * @throws IllegalArgumentException                   if the user does not have at least one role associated
     */
    UserAccount updateUser(Integer id, UserAccount user);

    /**
     * Removes an existing user account.
     *
     * @param id identifier of the user to delete
     * @throws jakarta.persistence.EntityNotFoundException if the user does not exist
     */
    void deleteUser(Integer id);

    /**
     * Retrieves a user account by its identifier.
     *
     * @param id identifier of the user
     * @return the found entity
     * @throws jakarta.persistence.EntityNotFoundException if the user does not exist
     */
    UserAccount getUserById(Integer id);

    /**
     * Lists all stored user accounts.
     *
     * @return list of users
     */
    List<UserAccount> getAllUsers();
}
