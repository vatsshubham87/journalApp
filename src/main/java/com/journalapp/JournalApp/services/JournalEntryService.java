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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String userName)
    {
        try{
            User user =  userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occurred while saving the entry");
        }

    }

    public void saveJournalEntry(JournalEntry journalEntry)
    {
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

    @Transactional
    public boolean deleteById(ObjectId id, String userName)
    {
        boolean removed = false;
        try {
            User user =  userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed)
            {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
                return removed;
            }
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occured while deleting the id", e);
        }
         return removed;
    }
}
