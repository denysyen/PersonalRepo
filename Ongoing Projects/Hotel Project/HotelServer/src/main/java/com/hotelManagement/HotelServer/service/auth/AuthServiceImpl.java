package com.hotelManagement.HotelServer.service.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotelManagement.HotelServer.dto.SignupRequest;
import com.hotelManagement.HotelServer.dto.UserDto;
import com.hotelManagement.HotelServer.entity.User;
import com.hotelManagement.HotelServer.enums.UserRole;
import com.hotelManagement.HotelServer.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    
    @PostConstruct // this annotation allows to excecute the method automatically,  no need for a call from an API  
    public void createAnAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created successfully");
        } else {
            System.out.println("Admin account already exist");
        }
    }
    
    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        Optional<User> user = userRepository.findFirstByEmail(signupRequest.getEmail());

        if(user.isPresent()) {
            // exception to handle duplicated Entity in DB
            throw new EntityExistsException(" User Already Presente with email" + signupRequest.getEmail()); 
        }

        User newUser = new User();
        newUser.setEmail(signupRequest.getEmail());
        newUser.setName(signupRequest.getName());
        newUser.setUserRole(UserRole.CUSTOMER);
        newUser.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

        User createdUser = userRepository.save(newUser);

        return createdUser.getUserDto();

    }


}
