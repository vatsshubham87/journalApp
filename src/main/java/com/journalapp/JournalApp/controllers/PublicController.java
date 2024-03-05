package com.journalapp.JournalApp.controllers;

import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String HealthCheck()
    {
        return "ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        try {
            userService.saveEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }



}
