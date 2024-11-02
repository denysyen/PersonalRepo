package com.carrentalprotecr.Car_rental_spring.services.auth;

import org.springframework.stereotype.Service;

import com.carrentalprotecr.Car_rental_spring.dto.SignupRequest;
import com.carrentalprotecr.Car_rental_spring.dto.UserDto;
import com.carrentalprotecr.Car_rental_spring.entity.User;
import com.carrentalprotecr.Car_rental_spring.enums.UserRole;
import com.carrentalprotecr.Car_rental_spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDto userDto =  new UserDto();
        userDto.setId(createdUser.getId());
        

        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

}
