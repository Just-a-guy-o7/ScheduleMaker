package com.ScheduleMaker.ScheduleMaker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ScheduleMaker.ScheduleMaker.Entities.User;

public interface UserRepo extends JpaRepository<User,Long>{

}
