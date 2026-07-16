package com.ScheduleMaker.ScheduleMaker.Services;


import org.springframework.stereotype.Service;

import com.ScheduleMaker.ScheduleMaker.Entities.User;;;

@Service
public interface UserServices {

    /**
     * Saves a new user entity.
     */
    boolean saveUser(User toSave);

    /**
     * Retrieves a user by its identifier.
     */
    User getUser(Long userId);

    /**
     * Updates an existing user entity.
     */
    boolean updateUser(User toUpdate);

    /**
     * Deletes the provided user entity.
     */
    boolean deleteUser(User user);

    /**
     * Deletes a user by its identifier.
     */
    boolean deleteUserById(Long userId);

    /**
     * Checks whether a user exists for the given identifier.
     */
    boolean isUserById(Long userId);
}
