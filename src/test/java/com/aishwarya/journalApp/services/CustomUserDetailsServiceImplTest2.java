package com.aishwarya.journalApp.services;

import com.aishwarya.journalApp.entity.User;
import com.aishwarya.journalApp.repository.UserEntryRepository;
import com.aishwarya.journalApp.service.CustomUserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomUserDetailsServiceImplTest2 {

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @MockitoBean
    private UserEntryRepository userEntryRepository;

    @Test
    void loadUserByUsernameTest(){
        when(userEntryRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("shyam").password("shyam").roles(new ArrayList<>()).build());
        UserDetails user = customUserDetailsService.loadUserByUsername("shyam");
        Assertions.assertNotNull(user);
    }

}
