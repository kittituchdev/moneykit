package com.codekit.moneykit.controller;

import com.codekit.moneykit.dto.ApiResponse;
import com.codekit.moneykit.dto.RegisterDTO;
import com.codekit.moneykit.dto.SignInDTO;
import com.codekit.moneykit.dto.SignInResponse;
import com.codekit.moneykit.entity.User;
import com.codekit.moneykit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<String[]>> getUsers() {
        String[] userData = {"Moneykit User", "Other User"};
        return ResponseEntity.ok(new ApiResponse<>(true, "Get users success", userData));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterDTO registerDto) {
        User user = userService.createUser(registerDto);
        return ResponseEntity.ok(new ApiResponse<>(true, "User registered successfully", user.getEmail()));
    }


    // SignIn
    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<SignInResponse>> signIn(@RequestBody SignInDTO dataSignIn) {
        if (dataSignIn.getEmail() == null || dataSignIn.getEmail().trim().isEmpty() ||
                dataSignIn.getPassword() == null || dataSignIn.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = "accessToken";
        SignInResponse response = new SignInResponse();
        response.setAccessToken(token);
        return ResponseEntity.ok(new ApiResponse<>(true, "Sign in success", response));
    }
    // SignOut


}
