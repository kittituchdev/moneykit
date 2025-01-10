package com.codekit.moneykit.controllers;

import com.codekit.moneykit.dto.ApiResponse;
import com.codekit.moneykit.dto.RegisterDTO;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<String[]>> getUsers() {
        String[] userData = {"Moneykit User", "Other User"};
        return ResponseEntity.ok(new ApiResponse<>(true, "Get users success", userData));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterDTO newUser) {
        if (newUser.getEmail() == null || newUser.getEmail().trim().isEmpty() ||
                newUser.getPassword() == null || newUser.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "User registered successfully", newUser.getEmail()));
    }


    // SignIn
    // SignOut


}
