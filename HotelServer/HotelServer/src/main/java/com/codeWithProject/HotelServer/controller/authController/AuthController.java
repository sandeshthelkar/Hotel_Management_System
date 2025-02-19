package com.codeWithProject.HotelServer.controller.authController;

import com.codeWithProject.HotelServer.dto.AuthRequest;
import com.codeWithProject.HotelServer.dto.AuthResponse;
import com.codeWithProject.HotelServer.entity.User;
import com.codeWithProject.HotelServer.enums.Role;
import com.codeWithProject.HotelServer.repository.UserRepository;
import com.codeWithProject.HotelServer.utill.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return ResponseEntity.ok(Map.of("token",token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("User already exists!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

}
