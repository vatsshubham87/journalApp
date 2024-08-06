package com.journalapp.JournalApp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendTestMail(){

        emailService.sendMail("vatsshubham87@gmail.com",
                "mail from java  spring boot ",
                "this is mail for testing ");
    }
}
