package com.codeWithProject.HotelServer.service.authService;

import com.codeWithProject.HotelServer.dto.AuthRequest;
import com.codeWithProject.HotelServer.dto.AuthResponse;
import com.codeWithProject.HotelServer.utill.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;


    public AuthResponse authenticate(AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }




}
