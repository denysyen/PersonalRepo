package com.service.impl;
import com.model.User;
import com.repositories.UserRepository;
import com.service.UserService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional  // database related operations
public class UserServiceImpl implements UserService {
// public static List<User> userList =  new ArrayList<>();
// private static Long counter  = (long) 1;

@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;

    
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
        // return userList.stream().sorted(Comparator.comparing(User::getId))
        // .collect(Collectors.toList());
    }
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
        // return userList.stream().filter(user -> user.getId() == id)
        // .findFirst();
    }
    @Override
    public void add(User user) {
        // user.setId(counter);
        // userList.add(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> update(User user){
        // Optional<User> userOpt = userList.stream() // you can process data in a declarative way similar to SQL statements.
        // .filter(u -> u.getId() == user.getId())
        // .findFirst();
        Optional<User> userOpt = userRepository.findById(user.getId());

        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();

            if(user.getUsername()!= null) {
                existingUser.setUsername(user.getUsername());
            }

            if(user.getPassword()!= null) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            if(user.getFirstName()!= null){
                existingUser.setFirstName(user.getFirstName());
            }

            if(user.getLastname() != null){
                existingUser.setLastname(user.getLastname());
            }

            if(user.getAge() != null){
                existingUser.setAge(user.getAge());
            }

            if(user.getCountry() != null){
                existingUser.setCountry(user.getCountry());
            }
        // userList = userList.stream().filter( u -> u.getId() != existingUser.getId())
        //     .collect(Collectors.toList());
        //     userList.add(existingUser);
            userRepository.save(existingUser);
            return Optional.of(existingUser);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Long id) {
         // Optional<User> userOpt = userList.stream().filter(user -> user.getId() == id).findFirst();
            Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            // userList.stream()
            // .filter(user -> userOpt.get().getId() != user.getId())
            // .collect(Collectors.toList());
            userRepository.delete(userOpt.get());
            return userOpt;
        }
        return Optional.empty();
    }

    @Override
    public List<User> findByCriteria(String criteria, String searchItem) {

        switch (criteria) {
            case "username":
                return this.userRepository.findByUsername(searchItem);
            case "firstName":
                return this.userRepository.findByFirstName(searchItem);
            case "lastName":
                return this.userRepository.findByLastname(searchItem);
            case "age":
                try {
                    Integer age = Integer.valueOf(searchItem);
                    return this.userRepository.findByAge(age);
                } catch (NumberFormatException e) {
                    System.out.println("Could not convert age to number ...");
                }
                return new ArrayList<>();
            case "country":
                return this.userRepository.findByCountry(searchItem);
        }
        return new ArrayList<>();
    }


}