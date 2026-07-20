package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import java.util.Optional;

import com.ScheduleMaker.ScheduleMaker.Services.UserServices;
import com.ScheduleMaker.ScheduleMaker.Repositories.UserRepo;
import com.ScheduleMaker.ScheduleMaker.Entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean saveUser(User toSave) {
        if (toSave != null && toSave.getEmail() != null && isUserById(toSave.getEmail())) {
            return updateUser(toSave);
        }
        try {
            userRepo.save(toSave);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUser(String email) {
        Optional<User> inDB = userRepo.findById(email);
        return inDB.orElse(null);
    }

    @Override
    public boolean updateUser(User toUpdate) {
        if (toUpdate != null && toUpdate.getEmail() != null && isUserById(toUpdate.getEmail())) {
            userRepo.save(toUpdate);
            return true;
        }
        try {
            userRepo.save(toUpdate);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        if (user != null && user.getEmail() != null && isUserById(user.getEmail())) {
            try {
                userRepo.delete(user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteUserById(String email) {
        if (email != null && isUserById(email)) {
            try {
                userRepo.deleteById(email);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isUserById(String email) {
        return email != null && userRepo.findById(email).isPresent();
    }
}
