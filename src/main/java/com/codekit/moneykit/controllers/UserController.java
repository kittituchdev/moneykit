package com.codekit.moneykit.controllers;

import com.codekit.moneykit.dto.ApiResponse;
import com.codekit.moneykit.dto.RegisterDTO;
import com.codekit.moneykit.dto.SignInDTO;
import com.codekit.moneykit.dto.SignInResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
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
    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<SignInResponse>> signIn(@RequestBody SignInDTO dataSignIn) {
        if (dataSignIn.getEmail() == null || dataSignIn.getEmail().trim().isEmpty() ||
                dataSignIn.getPassword() == null || dataSignIn.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        logger.info(dataSignIn.getEmail());

        String token = "accessToken";
        SignInResponse response = new SignInResponse();
        response.setAccessToken(token);
        return ResponseEntity.ok(new ApiResponse<>(true, "Sign in success", response));
    }
    // SignOut


}
