package com.aishwarya.journalApp.service;

import com.aishwarya.journalApp.entity.User;
import com.aishwarya.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final static Logger logger = LoggerFactory.getLogger(UserEntryService.class);

    @Autowired
    private UserEntryRepository userEntryRepository;

    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepository.save(user);
            return true;
        }
        catch (Exception e){
            logger.error("error occured for {} ", user.getUsername(),e);
            return false;
        }
    }

    public void saveUser(User user){
        userEntryRepository.save(user);
    }

    public List<User> getAll(){
        return userEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userEntryRepository.deleteById(id);
    }

    public User findByUsername(String username){
        return userEntryRepository.findByUsername(username);
    }
}
