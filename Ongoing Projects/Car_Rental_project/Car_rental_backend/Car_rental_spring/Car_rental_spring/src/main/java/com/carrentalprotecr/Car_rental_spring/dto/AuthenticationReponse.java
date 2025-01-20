package com.carrentalprotecr.Car_rental_spring.dto;

import com.carrentalprotecr.Car_rental_spring.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationReponse {
    private String jwt;
    private UserRole userRole;
    private Long userId;

}
