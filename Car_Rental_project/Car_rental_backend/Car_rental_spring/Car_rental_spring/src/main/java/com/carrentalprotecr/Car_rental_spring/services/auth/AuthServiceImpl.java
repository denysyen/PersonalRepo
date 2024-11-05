package com.carrentalprotecr.Car_rental_spring.services.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carrentalprotecr.Car_rental_spring.dto.SignupRequest;
import com.carrentalprotecr.Car_rental_spring.dto.UserDto;
import com.carrentalprotecr.Car_rental_spring.entity.User;
import com.carrentalprotecr.Car_rental_spring.enums.UserRole;
import com.carrentalprotecr.Car_rental_spring.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    
    @PostConstruct //  to post automatically in run time to DB 
    public void createAdminAccount() {
        User adminAccount =  userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount == null) {
            User newAdminAccount = new User();
            newAdminAccount.setName("Admin");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
            newAdminAccount.setUserRole(UserRole.ADMIN);
            userRepository.save(newAdminAccount);
            System.out.println("Admin account created successfully");
        }
    }

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
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
