package com.carrentalprotecr.Car_rental_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentalprotecr.Car_rental_spring.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findFirstByEmail(String email);
}
