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

        //After
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        String userName = "shyam";
//        User user = userService.findByUserName(userName);
//        List<JournalEntry> journalEntries = user.getJournalEntries();
//        if(journalEntries != null && !journalEntries.isEmpty())
//        {
//            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //before
        List<JournalEntry> journalEntries = journalEntryService.getAll();
        if(!journalEntries.isEmpty())
        {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry)
    {
        try
        {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
            String username = "";
        journalEntryService.saveJournalEntry(journalEntry, username);
        return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(journalEntry, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> get(@PathVariable ObjectId id)
    {
        //after

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        String userName = "shyam";
//        User user = userService.findByUserName(userName);
//        List<JournalEntry>  collect = user.getJournalEntries().stream().filter(x->x.getId().equals(id)).toList();
//        if(!collect.isEmpty())
//        {
//            Optional<JournalEntry> optional = journalEntryService.get(id);
//            if(optional.isPresent())
//            {
//                JournalEntry journalEntry = optional.get();
//                return new ResponseEntity<>(journalEntry, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //before
      Optional<JournalEntry> journalEntry = journalEntryService.get(id);

      if(journalEntry.isPresent())
      {
          JournalEntry journal = journalEntry.get();
          return new ResponseEntity<>(journal, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id)
    {
        //after
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
        String userName = "shyam";
        boolean removed = journalEntryService.deleteById(id, userName);
        if(removed)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //before
//        boolean removed = journalEntryService.deleteById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry)
    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        String userName = "shyam";
//        User user = userService.findByUserName(userName);
//        List<JournalEntry>  collect = user.getJournalEntries().stream().filter(x->x.getId().equals(id)).toList();
//        if(!collect.isEmpty())
//        {
//            Optional<JournalEntry> optional = journalEntryService.get(id);
//            if(optional.isPresent())
//            {
//                JournalEntry old = optional.get();
//                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
//                old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
//                journalEntryService.saveJournalEntry(old);
//                return new ResponseEntity<>(old, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //before

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
