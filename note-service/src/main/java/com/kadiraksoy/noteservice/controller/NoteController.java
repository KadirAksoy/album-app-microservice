package com.kadiraksoy.noteservice.controller;

import com.kadiraksoy.noteservice.dto.SearchRequestDto;
import com.kadiraksoy.noteservice.model.Note;
import com.kadiraksoy.noteservice.service.NoteService;
import com.kadiraksoy.noteservice.exception.NoteCategoryNotFoundException;
import com.kadiraksoy.noteservice.exception.NoteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        Optional<Note> note = noteService.findById(id);
        return note.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }

    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        try {
            Note savedNote = noteService.save(note);
            return ResponseEntity.ok(savedNote);
        } catch (NoteCategoryNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable String id) {
        try {
            noteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(@RequestBody SearchRequestDto searchRequestDto) {
        List<Note> notes = noteService.searchNotesByFieldAndValue(searchRequestDto);
        return ResponseEntity.ok(notes);
    }
}
