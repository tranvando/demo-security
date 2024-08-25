package com.example.demosecurity.service;

import com.example.demosecurity.modal.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private static List<User> listUser = new ArrayList<>();
    public String addUser(User user) {
        listUser.add(user);
        return "Success";
    }
    public User getUserByUsername(String username){
        //List<User> listUser = new ArrayList<>();
        //listUser.add(new User("user","$2a$12$M4ZadIw9wAtmcD/5aP1qYuQJlusxDHXHKuBCLq3zXOIs8fifYbTNC","USER"));
        //listUser.add(new User("admin","123","ADMIN"));
        Optional<User> user = Optional.ofNullable(listUser.stream().filter(u -> u.getUsername().equals(username)).toList().stream().findAny().orElse(null));
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //List<User> listUser = new ArrayList<>();
        //listUser.add(new User("user","123","USER"));
        //listUser.add(new User("admin","123","ADMIN"));
        Optional<User> user = Optional.ofNullable(listUser.stream().filter(u -> u.getUsername().equals(username)).toList().stream().findAny().orElse(null));
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException(username);
    }
}
