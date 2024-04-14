package com.example.security.repository;

import com.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
   //findBy규칙 -> Username문법
    //select* from user where username =1?
    public User findByUsername(String username);

    //select * from user where email=?
    //public User findByEmail();

}
