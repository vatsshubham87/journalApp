package com.journalapp.JournalApp.repositories;

import com.journalapp.JournalApp.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>
{
     User findByUsername(String name);
}
