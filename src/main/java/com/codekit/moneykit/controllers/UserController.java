package com.codekit.moneykit.controllers;

import com.codekit.moneykit.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public ApiResponse getUsers() {
        String[] userData = {"Moneykit User", "Other User"};
        return new ApiResponse(true, userData);
    }

    @GetMapping("/users/{id}")
    public ApiResponse getUserById(@PathVariable("id") String id) {
        // Simulated user data based on ID
        String userData = "User with ID: " + id;
        return new ApiResponse(true, userData);
    }

}
