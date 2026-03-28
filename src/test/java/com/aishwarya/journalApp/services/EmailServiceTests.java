package com.aishwarya.journalApp.services;

import com.aishwarya.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
       emailService.sendEmail("mehrotraa7@gmail.com","testing email service","hello how are you");
    }
}
