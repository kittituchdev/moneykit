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

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse<List<String>>> getUsers() {
        List<String> userData = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Get users success", userData));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterDTO registerDto) {
        User user = userService.createUser(registerDto);
        return ResponseEntity.ok(new ApiResponse<>(true, "User registered successfully", user.getEmail()));
    }


    // SignIn
    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<SignInResponse>> signIn(@RequestBody SignInDTO signInDTO) {
        SignInResponse signInResponse = userService.signIn(signInDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Sign in success", signInResponse));
    }
    // SignOut


}
