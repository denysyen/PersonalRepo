package com.hotelManagement.HotelServer.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotelManagement.HotelServer.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    // Using this construction one can embed one method inside another 
    //  in that way outside of the class to access the inner method
    // one needs to invoke an instance of the outer methdo i.e : 
    // userDetailsService().loadUserByUsername(username) 
    public UserDetailsService userDetailsService() {
         return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if(username.indexOf("@") != -1) {
                    return userRepository.findFirstByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
                }
                return userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
         };

    }

}
