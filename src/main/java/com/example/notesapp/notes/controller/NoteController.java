package com.example.notesapp.notes.controller;

import com.example.notesapp.util.advices.ResponseHandler;
import com.example.notesapp.notes.dto.AddNoteDto;
import com.example.notesapp.notes.dto.NoteDto;
import com.example.notesapp.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {


    private final NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id){

        NoteDto note = noteService.findNoteById(id);

        return ResponseHandler.responseBuilder(note, HttpStatus.OK,null, LocalDateTime.now());

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllNotes(@PathVariable Long userId){

        List<NoteDto> notes =  noteService.findAllNotesByUser(userId);

        return ResponseHandler.responseBuilder(notes,HttpStatus.OK,null,LocalDateTime.now());

    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createNote(@PathVariable Long id, @RequestBody AddNoteDto note){

        NoteDto newNote = noteService.createNoteForUser(note,id);

        return ResponseHandler.responseBuilder(newNote,HttpStatus.OK,null,LocalDateTime.now());
    }

    @PutMapping("/")
    public ResponseEntity<?> updateNote(@RequestBody NoteDto note){

        NoteDto updatedNote = noteService.updateNote(note);

        return ResponseHandler.responseBuilder(updatedNote,HttpStatus.OK,null,LocalDateTime.now());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id){

        noteService.deleteNote(id);

        var deleteMsg = "Note with id "+id+" successfully deleted";

        return ResponseHandler.responseBuilder(deleteMsg,HttpStatus.OK,null,LocalDateTime.now());
    }



}
