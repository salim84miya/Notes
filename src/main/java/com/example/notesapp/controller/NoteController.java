package com.example.notesapp.controller;

import com.example.notesapp.dto.NoteDto;
import com.example.notesapp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController {


    private final NoteService noteService;

    @GetMapping("/{id}")
    public NoteDto getNoteById(@PathVariable Long id){

        return noteService.findNoteById(id);
    }

    @GetMapping("/{userId}")
    public List<NoteDto> getAllNotes(@PathVariable Long userId){

        return noteService.findAllNotesByUser(userId);
    }



}
