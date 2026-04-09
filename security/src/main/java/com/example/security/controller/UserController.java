package com.example.security.controller;

import com.example.security.model.User;
import com.example.security.service.JWTService;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    @Autowired
    private UserService userservice;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        userservice.saveUser(user);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws NoSuchAlgorithmException {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(

                        user.getUsername(),
                        user.getPassword()
                )
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
//            return "Success";
        }
        else{
            return "Unauthorize login";
        }
    }
}
