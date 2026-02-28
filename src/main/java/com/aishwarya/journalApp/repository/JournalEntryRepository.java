package com.aishwarya.journalApp.repository;

import com.aishwarya.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
