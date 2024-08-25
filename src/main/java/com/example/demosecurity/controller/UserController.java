package com.example.demosecurity.controller;

import com.example.demosecurity.modal.User;
import com.example.demosecurity.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserDetailService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/users")
    public String getUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.addUser(user);
    }

    @GetMapping("/users")
    public User getUser(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/login-success")
    public String getUser(){
        return "login success";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String user(){
        return "user";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin(){
        return "admin";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String all(){
        return "all";
    }

    @GetMapping("/test")
    public String test(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "test";
    }
}
