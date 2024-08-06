package com.journalapp.JournalApp.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.journalapp.JournalApp.enums.Sentiment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
//    private Sentiment sentiment;

}
