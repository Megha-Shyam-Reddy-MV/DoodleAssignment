package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import com.example.demo.utils.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User update(User user) {
        User foundUser = userRepository.findById(user.getId()).orElse(null);
        if (foundUser != null) {
            foundUser.setName(user.getName());
            foundUser.setEmail(user.getEmail());
            foundUser.setGender(user.getGender());
            foundUser.setAge(user.getAge());
            return userRepository.save(foundUser);
        } else {
            return null;
        }
    }
}

