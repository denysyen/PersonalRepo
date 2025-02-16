package com.hotelManagement.HotelServer.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelManagement.HotelServer.dto.AuthenticationRequest;
import com.hotelManagement.HotelServer.dto.AuthenticationResponse;
import com.hotelManagement.HotelServer.dto.SignupRequest;
import com.hotelManagement.HotelServer.dto.UserDto;
import com.hotelManagement.HotelServer.entity.User;
import com.hotelManagement.HotelServer.repository.UserRepository;
import com.hotelManagement.HotelServer.service.auth.AuthService;
import com.hotelManagement.HotelServer.service.jwt.UserService;
import com.hotelManagement.HotelServer.util.JwtUtil;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    // Here the final access modify secure the at leats one instanciation 
    // of the class (ofc as const) otherwise the instance will be set to null
    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
  
    private final JwtUtil jwtUtil;

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        try {
            UserDto createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException entityExistsException) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("User not created, try again later", HttpStatus.BAD_REQUEST);
        }

    }
    
    @PostMapping("/login")
    public AuthenticationResponse creaAuthenticationResponse(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        User details = (User) userDetails;
        Optional<User> optionalUser = userRepository.findFirstByEmail(details.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
         return authenticationResponse;
    }
}
