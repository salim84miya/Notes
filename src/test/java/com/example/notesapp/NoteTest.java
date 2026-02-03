package com.example.notesapp;

import com.example.notesapp.notes.dto.AddNoteDto;
import com.example.notesapp.notes.dto.NoteDto;
import com.example.notesapp.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class NoteTest {


    @Autowired
    private NoteService noteService;


    @Test
    public void createNoteWithUser(){

        AddNoteDto note = AddNoteDto.builder()
                .title("Dhoom Machale script")
                .description("Once there was a clever thief, who got away with a lot robberies until a girl came into his life")
                .build();

       NoteDto newNote = noteService.createNoteForUser(note,1L);

       System.out.println(newNote);

    }

    @Test
    public void deleteNote(){

        noteService.deleteNote(2L);
    }

    @Test
    public void updateNote(){

        NoteDto note = new NoteDto();
        note.setId(2L);
        note.setTitle("Updated title");
        note.setDescription("Updated description");

       NoteDto newNote = noteService.updateNote(note);

       System.out.println(newNote);
    }

    @Test
    public void findNote(){

        NoteDto newNote = noteService.findNoteById(3L);

        System.out.println(newNote);
    }

    @Test
    public void findAllNotes(){

        List<NoteDto> notes = noteService.findAllNotesByUser(1L);

        notes.forEach(System.out::println);
    }


}
