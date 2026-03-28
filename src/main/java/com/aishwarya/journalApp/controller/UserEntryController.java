package com.aishwarya.journalApp.controller;


import com.aishwarya.journalApp.entity.User;
import com.aishwarya.journalApp.entity.api.response.WeatherResponse;
import com.aishwarya.journalApp.service.UserEntryService;
import com.aishwarya.journalApp.service.WeatherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "user APIs" , description = "Read,Update & Delete user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public List<User> getAllUsers(){
        return userEntryService.getAll();
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userEntryService.findByUsername(username);
        if(userInDb != null){
            userInDb.setUsername(user.getUsername() != null && user.getUsername().length() > 0 ? user.getUsername() : "");
            userInDb.setPassword(user.getPassword());
            userEntryService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String greeting = "";
        String username = authentication.getName();
        WeatherResponse weatherResponse = weatherService.getWeather("Lucknow");
        if(weatherResponse != null){
            greeting = "Hi " + authentication.getName() + " weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + username + " weather feels like " + greeting ,HttpStatus.OK);
    }
}
