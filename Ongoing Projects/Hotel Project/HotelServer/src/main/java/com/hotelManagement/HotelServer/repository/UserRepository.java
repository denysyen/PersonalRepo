package com.hotelManagement.HotelServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelManagement.HotelServer.entity.User;
import com.hotelManagement.HotelServer.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);
    Optional<User> findByName(String name);

    Optional<User> findByUserRole(UserRole userRole);
}
