package com.journalapp.JournalApp.repositories;

import com.journalapp.JournalApp.entities.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, Object> {
}
