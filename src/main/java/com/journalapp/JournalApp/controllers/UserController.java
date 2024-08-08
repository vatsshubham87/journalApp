package com.journalapp.JournalApp.controllers;

import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserById(@PathVariable String userName)
    {
        try {
            User user = userService.findByUserName(userName);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<Object> createNewUser(@RequestBody User user) {
        try {
            boolean b = userService.saveEntry(user);
            if (b) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User creation failed.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User userInDb = userService.findByUserName(name);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userInDb.setEmail(user.getEmail());
        userService.saveEntry(userInDb);
        return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userInDb = userService.findByUserName(authentication.getName());
        userService.deleteById(userInDb.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
