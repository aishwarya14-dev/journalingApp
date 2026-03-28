package com.aishwarya.journalApp.services;

import com.aishwarya.journalApp.entity.User;
import com.aishwarya.journalApp.repository.UserEntryRepository;
import com.aishwarya.journalApp.service.UserEntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Autowired
    private UserEntryService userEntryService;

    @Disabled
    @Test
    public void testFindByUsername1(){
        assertNotNull(userEntryRepository.findByUsername("shyam"));
    }

    @BeforeEach
    public void setup(){

    }

    @ParameterizedTest
    @ValueSource(strings = {
         "ram",
         "shyam"
    })
    public void testFindByUsername2(){
        assertNotNull(userEntryRepository.findByUsername("shyam"));
    }


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userEntryService.saveNewUser(user));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test1( int a , int b,int expected){
        assertEquals(expected,a,b);
    }
}
