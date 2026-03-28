package com.aishwarya.journalApp.cache;

import com.aishwarya.journalApp.entity.ConfigJournalAppEntity;
import com.aishwarya.journalApp.repository.ConfigJournalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public Map<String,String> APP_CACHE = new HashMap<>();

    @Autowired
    private ConfigJournalRepository configJournalRepository;

    @PostConstruct
    public void init(){
        List<ConfigJournalAppEntity> all = configJournalRepository.findAll();
        for(ConfigJournalAppEntity entity : all){
            APP_CACHE.put(entity.getKey(),entity.getValue());
        }
        APP_CACHE = APP_CACHE;
    }
}
