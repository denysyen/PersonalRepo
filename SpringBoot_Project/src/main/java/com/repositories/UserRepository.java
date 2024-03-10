package com.repositories;
import com.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    public List<User> findByUsername(String username);

    public List<User> findByFirstName(String firtsName);

    public List<User> findByLastname(String lastName);

    public List<User> findByAge (Integer age);

    public List<User> findByCountry(String country);

}