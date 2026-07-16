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
     * Retrieves a user by its email address.
     */
    User getUser(String email);

    /**
     * Updates an existing user entity.
     */
    boolean updateUser(User toUpdate);

    /**
     * Deletes the provided user entity.
     */
    boolean deleteUser(User user);

    /**
     * Deletes a user by its email address.
     */
    boolean deleteUserById(String email);

    /**
     * Checks whether a user exists for the given email address.
     */
    boolean isUserById(String email);
}
