package com.journalapp.JournalApp.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection="users")
@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;

    @Field(name="email_address")
    private String email;

//    private boolean sentimentAnalysis;

    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry> journalEntries;
    private List<String> roles;
}
