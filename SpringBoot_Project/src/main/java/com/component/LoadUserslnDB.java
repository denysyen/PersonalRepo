package com.component;

import com.model.User;
import com.repositories.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;







@Component
@Transactional
public class LoadUserslnDB implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {


    if (userRepository.count() > 0) {
        return;
    }
    User user1 = new User("gergianaO",UUID.randomUUID().toString(),"Georgina", "Ortega", 24, "Bresil");
    User user2 = new User( "rosaS",UUID.randomUUID().toString(),"Rosa", "Sparka", 34, "Mexico");
    User user3 = new User("orlaM",UUID.randomUUID().toString(),"Orla", "McCoy", 19, "USA");
    User user4 = new User("jerryH",UUID.randomUUID().toString(),"Jerry", "Hanna", 42, "Canada");
    User user5 = new User("savannahD",UUID.randomUUID().toString(),"Savannah", "Daniel", 19, "USA");
    User user6 = new User("abbyG",UUID.randomUUID().toString(),"Abby", "Green",32, "USA");
    User user7 = new User("velmaG",UUID.randomUUID().toString(),"Velma", "Griffin",42, "USA");
    User user8 = new User("serena",UUID.randomUUID().toString(),"Serena", "Singh",36, "Canada");
    User user9 = new User("veronima",UUID.randomUUID().toString(),"Veronima", "Cooper",24, "Canada");
    User user10 = new User("alex",UUID.randomUUID().toString(),"Alex", "Rosu",30, "Romania");
    
    List<User> userList = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
    userList = userList.stream().map(user -> {
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return user;
    }).collect(Collectors.toList());
    
    userRepository.saveAll(userList);

    }
    
}

