package com.example.ecommerce.services;

import com.example.ecommerce.models.User;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getInforUser(String email){
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    public void register(User user){
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
