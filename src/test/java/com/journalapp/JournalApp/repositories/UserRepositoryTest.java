package com.journalapp.JournalApp.repositories;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    void testSaveNewUser()
    {
       userRepository.getUserForSA();
    }



}
