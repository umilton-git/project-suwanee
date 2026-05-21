package com.suwanee.service;

import com.suwanee.dto.request.LoginRequest;
import com.suwanee.dto.request.RegisterRequest;
import com.suwanee.dto.response.AuthResponse;
import com.suwanee.dto.response.UserResponse;
import com.suwanee.model.entity.User;
import com.suwanee.repository.UserRepository;
import com.suwanee.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
@Transactional

public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponse createUser(RegisterRequest registerRequest) {
        User user = new User();

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        user.setEmail(registerRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        return UserResponse.from(userRepository.save(user));
    }

    public AuthResponse login (LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(loginRequest.getEmail() + " not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Incorrect password");
        }
        return new AuthResponse(jwtUtil.generateToken(user.getEmail()));
    }
}
