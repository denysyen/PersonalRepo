package com.carrentalprotecr.Car_rental_spring.services.auth;

import com.carrentalprotecr.Car_rental_spring.dto.SignupRequest;
import com.carrentalprotecr.Car_rental_spring.dto.UserDto;

public interface AuthService {
    UserDto createCustomer(SignupRequest signupRequest);
    boolean hasCustomerWithEmail(String email);

}
