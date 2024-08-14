package com.journalapp.JournalApp.services;

import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.repositories.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    void testSaveEntry(User user)
    {
       assertTrue(userService.saveEntry(user));
    }

//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Ram",
//            "shyam",
//            "vicky"
//    })
//    void testFindByUserName(String name)
//    {
//       User user = userRepository.findByUsername(name);
//       assertNotNull(user, "failed for "+name);
////       assertTrue(!user.getJournalEntries().isEmpty());
//    }


    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,10,12",
            "3,3,9"
    })
    void test(int a , int b, int expected)
    {
         assertEquals(expected, a+b);
    }

}
