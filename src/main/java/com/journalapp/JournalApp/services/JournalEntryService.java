package com.journalapp.JournalApp.services;

import com.journalapp.JournalApp.entities.JournalEntry;
import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.repositories.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    public void saveJournalEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            logger.debug("JournalEntry saved: {}", saved);

            // Check if the journalEntries list is null and initialize it if necessary
            if (user.getJournalEntries() == null) {
                user.setJournalEntries(new ArrayList<>());
            }

            user.getJournalEntries().add(saved);
            userService.saveUser(user);
            logger.debug("User updated with new JournalEntry: {}", user);
        } catch (Exception e) {
            logger.error("An error occurred while saving the entry", e);
            throw new RuntimeException("An error occurred while saving the entry", e);
        }
    }




    public void saveJournalEntry(JournalEntry journalEntry)
    {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> get(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName)
    {
        User user =  userService.findByUserName(userName);
        boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(removed)
        {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }

    }
}
