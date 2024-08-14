package com.journalapp.JournalApp.services;


import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.repositories.UserRepository;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    UserDetailServiceImpl userDetailService;


    @Mock
    UserRepository userRepository;


    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserNameTest()
    {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("shubham11").password("wdq2eddw").roles(new ArrayList<>()).build());
        UserDetails user = userDetailService.loadUserByUsername("shubham11");
        Assertions.assertNotNull(user);
    }

}
