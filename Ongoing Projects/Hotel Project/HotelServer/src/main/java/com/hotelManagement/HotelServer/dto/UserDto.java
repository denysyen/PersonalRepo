package com.hotelManagement.HotelServer.dto;

import com.hotelManagement.HotelServer.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;

}
