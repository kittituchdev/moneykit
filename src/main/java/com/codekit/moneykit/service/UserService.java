package com.codekit.moneykit.service;

import com.codekit.moneykit.controller.UserController;
import com.codekit.moneykit.dto.RegisterDTO;
import com.codekit.moneykit.entity.User;
import com.codekit.moneykit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(RegisterDTO registerDTO) {
        logger.info("Create user: {}", registerDTO.getEmail());
        if (registerDTO.getEmail() == null || registerDTO.getEmail().trim().isEmpty() ||
                registerDTO.getPassword() == null || registerDTO.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        User newUser = new User();
        newUser.setId(0);
        newUser.setEmail(registerDTO.getEmail());
        newUser.setName(registerDTO.getEmail());
        newUser.setPassword(registerDTO.getPassword()); // this is demo data, when use in production should encode the password
        return this.userRepository.save(newUser);
    }

}
