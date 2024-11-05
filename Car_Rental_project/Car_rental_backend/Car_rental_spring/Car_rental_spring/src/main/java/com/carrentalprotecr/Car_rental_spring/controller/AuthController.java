package com.carrentalprotecr.Car_rental_spring.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalprotecr.Car_rental_spring.dto.AuthenticationReponse;
import com.carrentalprotecr.Car_rental_spring.dto.AuthenticationRequest;
import com.carrentalprotecr.Car_rental_spring.dto.SignupRequest;
import com.carrentalprotecr.Car_rental_spring.dto.UserDto;
import com.carrentalprotecr.Car_rental_spring.entity.User;
import com.carrentalprotecr.Car_rental_spring.repository.UserRepository;
import com.carrentalprotecr.Car_rental_spring.services.auth.AuthService;
import com.carrentalprotecr.Car_rental_spring.services.jwt.UserService;
import com.carrentalprotecr.Car_rental_spring.utils.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        if(authService.hasCustomerWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("Customer already exist with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdCustomerDto =  authService.createCustomer(signupRequest);
        if(createdCustomerDto == null) {
            return new ResponseEntity<>("Customer not created, come again later", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationReponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws 
    BadCredentialsException, DisabledException, UsernameNotFoundException { 
        try {
             System.out.println("credentials ==" + authenticationRequest);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch(BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
      
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        
        AuthenticationReponse authenticationReponse = new AuthenticationReponse();
        if(optionalUser.isPresent()) {
            authenticationReponse.setJwt(jwt);
            authenticationReponse.setUserId(optionalUser.get().getId());
            authenticationReponse.setUserRole(optionalUser.get().getUserRole());
        }

        return authenticationReponse;
    }

}
