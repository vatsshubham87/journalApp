package com.journalapp.JournalApp.controllers;

import com.journalapp.JournalApp.entities.JournalEntry;
import com.journalapp.JournalApp.entities.User;
import com.journalapp.JournalApp.services.JournalEntryService;
import com.journalapp.JournalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser()
    {
        List<JournalEntry> journalEntries = journalEntryService.getAll();
        if(!journalEntries.isEmpty())
        {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry) {
        try {
        journalEntryService.saveJournalEntry(journalEntry);
        return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(journalEntry, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> get(@PathVariable ObjectId id)
    {
      Optional<JournalEntry> journalEntry = journalEntryService.get(id);

      if(journalEntry.isPresent()) {
          JournalEntry journal = journalEntry.get();
          return new ResponseEntity<>(journal, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id)
//    {
//
////        boolean removed = journalEntryService.deleteById(id,);
//
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry)
    {
        Optional<JournalEntry> old = journalEntryService.get(id);
        if(old.isPresent())
        {
            JournalEntry journalEntry = old.get();
            journalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : journalEntry.getTitle());
            journalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : journalEntry.getContent());
                journalEntryService.saveJournalEntry(journalEntry);
                return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
