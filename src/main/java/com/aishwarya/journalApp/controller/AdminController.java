package com.aishwarya.journalApp.controller;

import com.aishwarya.journalApp.entity.User;
import com.aishwarya.journalApp.service.UserEntryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
      List<User> allUsers = userEntryService.getAll();
      if(allUsers != null && !allUsers.isEmpty()){
         return new ResponseEntity<>(allUsers, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
