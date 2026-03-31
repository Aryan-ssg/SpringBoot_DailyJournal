package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.Data;

@Document(collection="users")
@Data
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @Nonnull
    private String username;
   
    @Nonnull
    private String password;
    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();

    
}
