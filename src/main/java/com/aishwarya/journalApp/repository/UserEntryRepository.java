package com.aishwarya.journalApp.repository;

import com.aishwarya.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
}
