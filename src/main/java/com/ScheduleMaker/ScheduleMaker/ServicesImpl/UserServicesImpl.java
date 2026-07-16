package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import java.util.Optional;

import com.ScheduleMaker.ScheduleMaker.Services.UserServices;
import com.ScheduleMaker.ScheduleMaker.Repositories.UserRepo;
import com.ScheduleMaker.ScheduleMaker.Entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServicesImpl implements UserServices{

    UserRepo userRepo;
    @Autowired
    public void setUserRepo(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    
    @Override
    public boolean saveUser(User toSave) {
        
        if(isUserById(toSave.getId())){
            return updateUser(toSave);
        }
        try{
            userRepo.save(toSave);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> inDB= userRepo.findById(userId);
        if(inDB.isPresent())return inDB.get();
        return null;
    }

    @Override
    public boolean updateUser(User toUpdate) {
        if(isUserById(toUpdate.getId())){
            
            userRepo.save(toUpdate);
            return true;
        }
        try{
            userRepo.save(toUpdate);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
         if(isUserById(user.getId())){
            try{
                userRepo.delete(user);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }
        else return true;
    }

    @Override
    public boolean deleteUserById(Long userId) {
        if(isUserById(userId)){
            try{
                userRepo.deleteById(userId);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }
        else return true;    
    }

    @Override
    public boolean isUserById(Long userId) {
        if(!userRepo.findById(userId).isPresent())return false;
        return true;
    }

}
