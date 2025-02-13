package com.codeWithProject.HotelServer.service;

import com.codeWithProject.HotelServer.entity.User;
import com.codeWithProject.HotelServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    public User getUserByEmail(String email){
//        return userRepository.findByEmail(email).orElseThrow(
//                ()-> new RuntimeException("User not found")
//        );
//    }

    public User createNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }



}
