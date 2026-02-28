package com.aishwarya.journalApp.service;

import com.aishwarya.journalApp.entity.JournalEntry;
import com.aishwarya.journalApp.entity.User;
import com.aishwarya.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    //spring creates implementation of JournalEntryRepository at runtime and inject here
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userEntryService.findByUsername(username);
            JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(savedEntry);
            userEntryService.saveUser(user);
        }
        catch (Exception e){
            throw new RuntimeException("an error occurred while saving the entry",e);
        }
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        try{
            User user = userEntryService.findByUsername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userEntryService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
//            log.error("Error ",e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }

}
