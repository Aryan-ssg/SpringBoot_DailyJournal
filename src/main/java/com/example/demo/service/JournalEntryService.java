package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Repository.JournalEntryRepository;
import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        journalEntry.setDate(LocalDateTime.now());
        User user = userService.findByUsername(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userName);
        }
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();

    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);

    }

    public void deleteById(ObjectId id, String username) {
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

    public JournalEntry updateById(ObjectId id, JournalEntry newEntry) {
        JournalEntry oldEntry = journalEntryRepository.findById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setContent(newEntry.getContent() != null ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle(newEntry.getTitle() != null ? newEntry.getTitle() : oldEntry.getTitle());
        }
        journalEntryRepository.save(oldEntry);
        return oldEntry;

    }

}
