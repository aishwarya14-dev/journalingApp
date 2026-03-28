package com.aishwarya.journalApp.scheduler;

import com.aishwarya.journalApp.cache.AppCache;
import com.aishwarya.journalApp.entity.JournalEntry;
import com.aishwarya.journalApp.entity.User;
import com.aishwarya.journalApp.repository.UserRepositoryImpl;
import com.aishwarya.journalApp.service.EmailService;
import com.aishwarya.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void setUsersAndSendSAMail(){
       List<User> userList = userRepositoryImpl.getUserForSA();
       for(User user : userList){
           List<JournalEntry> journalEntries = user.getJournalEntries();
           List<String> filteredEntries = journalEntries.stream().
                   filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).
                   map(x -> x.getContent()).collect(Collectors.toList());
           String entry = String.join(" ",filteredEntries);
           String sentiment = sentimentAnalysisService.getSentiment(entry);
           emailService.sendEmail(user.getEmail(),"sentiment for last 7 days",sentiment);
       }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}
