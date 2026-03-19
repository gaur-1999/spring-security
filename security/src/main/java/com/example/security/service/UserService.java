package com.example.security.service;

import com.example.security.model.User;
import com.example.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public void saveUser(User user){
        String newPaswword=encoder.encode(user.getPassword());
        user.setPassword(newPaswword);
        userRepo.save(user);
    }
}
