package com.journalapp.JournalApp.services;

import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean saveEntry(User user) {
        try{
          user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("User"));
            userRepository.save(user);
            return true;
        }catch(Exception e){
            System.out.println(e);
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
