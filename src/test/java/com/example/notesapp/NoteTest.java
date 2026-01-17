package com.example.notesapp;

import com.example.notesapp.entity.Note;
import com.example.notesapp.service.NoteService;
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

        Note note = Note.builder()
                .title("Dhoom Machale script")
                .description("Once there was a clever thief, who got away with a lot robberies until a girl came into his life")
                .build();

       Note newNote = noteService.createNoteForUser(note,1L);

       System.out.println(newNote);

    }

    @Test
    public void deleteNote(){

        noteService.deleteNote(2L);
    }

    @Test
    public void updateNote(){

        Note note = new Note();
        note.setId(2L);
        note.setTitle("Updated title");
        note.setDescription("Updated description");

       Note newNote = noteService.updateNote(note);
    }

    @Test
    public void findNote(){

        Note newNote = noteService.findNoteById(3L);

        System.out.println(newNote);
    }

    @Test
    public void findAllNotes(){

        List<Note> notes = noteService.findAllNotesByUser(1L);

        notes.forEach(System.out::println);
    }


}
