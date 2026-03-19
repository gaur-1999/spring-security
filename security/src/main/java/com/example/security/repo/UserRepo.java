package com.example.security.repo;

import com.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
        User findByUsername(String username);
}
