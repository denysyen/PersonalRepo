package com.hotelManagement.HotelServer.dto;

import com.hotelManagement.HotelServer.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;

}
