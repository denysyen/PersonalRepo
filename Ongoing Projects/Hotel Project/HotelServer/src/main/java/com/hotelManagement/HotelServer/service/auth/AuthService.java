package com.hotelManagement.HotelServer.service.auth;


import com.hotelManagement.HotelServer.dto.SignupRequest;
import com.hotelManagement.HotelServer.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

}
