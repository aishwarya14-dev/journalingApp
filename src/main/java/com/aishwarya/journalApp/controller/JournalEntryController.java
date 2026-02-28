package com.aishwarya.journalApp.controller;

import com.aishwarya.journalApp.entity.JournalEntry;
import com.aishwarya.journalApp.entity.User;
import com.aishwarya.journalApp.service.JournalEntryService;
import com.aishwarya.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userEntryService.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if (journalEntries != null && !journalEntries.isEmpty()) {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userEntryService.findByUsername(username);
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<JournalEntry>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<JournalEntry>(myEntry, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String myId) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userEntryService.findByUsername(username);
        List<JournalEntry> journalEntry = user.getJournalEntries().stream().filter(x -> x.getId()
                                            .equals(objectId))
                                            .collect(Collectors.toList());
        if(!journalEntry.isEmpty()){
            Optional<JournalEntry> entry = journalEntryService.findById(objectId);
            if (entry.isPresent()) {
                return new ResponseEntity<JournalEntry>(entry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String myId, @RequestBody JournalEntry myEntry) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(objectId,username);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping
    public ResponseEntity<?> updateJournalEntryById(@PathVariable String id,@RequestBody JournalEntry newEntry){
        ObjectId objectId = new ObjectId(id);
        JournalEntry oldEntry = journalEntryService.findById(objectId).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userEntryService.findByUsername(username);
        List<JournalEntry> journalEntry = user.getJournalEntries().stream().filter(x -> x.getId()
                        .equals(objectId))
                .collect(Collectors.toList());
        if(!journalEntry.isEmpty()){
            Optional<JournalEntry> entry = journalEntryService.findById(objectId);
            if(entry.isPresent()){
                JournalEntry old = entry.get();
                old.setTitle(newEntry.getTitle() != null && newEntry.getTitle().length() > 0 ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && newEntry.getContent().length() > 0 ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


