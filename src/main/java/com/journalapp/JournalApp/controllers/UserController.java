package com.journalapp.JournalApp.controllers;

import com.journalapp.JournalApp.entities.JournalEntry;
import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userName)
    {
//        User userInDb = userService.findByUserName(user.getUsername());
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userInDb.setEmail(user.getEmail());
        userService.saveEntry(userInDb);
        return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName)
    {
        User userInDb = userService.findByUserName(userName);
        userService.deleteById(userInDb.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
