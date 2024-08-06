package com.journalapp.JournalApp.services;

import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.repositories.JournalEntryRepository;
import com.journalapp.JournalApp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    // No @Bean annotation here

//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public boolean saveEntry(User user)
    {
        try{
            userRepository.save(user);
            return true;
        }catch(Exception e){
            return false;
    }


    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public Optional<User> get(ObjectId id)
    {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id)
    {
        userRepository.deleteById(id);
    }

    public User findByUserName(String username)
    {
        return userRepository.findByUserName(username);
    }

    public void saveAdmin(User user) {

        user.setRoles(Arrays.asList("Admin", "User"));
        userRepository.save(user);
    }
}
