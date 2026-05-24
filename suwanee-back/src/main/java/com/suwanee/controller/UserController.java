package com.suwanee.controller;

import com.suwanee.dto.request.LoginRequest;
import com.suwanee.dto.request.RegisterRequest;
import com.suwanee.dto.response.AuthResponse;
import com.suwanee.dto.response.UserResponse;
import com.suwanee.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.createUser(request);
    }
}
