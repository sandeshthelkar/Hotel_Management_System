package com.codeWithProject.HotelServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
//@AllArgsConstructor

public class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
