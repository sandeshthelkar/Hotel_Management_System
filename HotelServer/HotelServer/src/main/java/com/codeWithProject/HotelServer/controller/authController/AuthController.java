package com.codeWithProject.HotelServer.controller.authController;

import com.codeWithProject.HotelServer.dto.AuthRequest;
import com.codeWithProject.HotelServer.dto.AuthResponse;
import com.codeWithProject.HotelServer.entity.User;
import com.codeWithProject.HotelServer.repository.UserRepository;
import com.codeWithProject.HotelServer.utill.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
