package com.aishwarya.journalApp.repository;

import com.aishwarya.journalApp.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {}
