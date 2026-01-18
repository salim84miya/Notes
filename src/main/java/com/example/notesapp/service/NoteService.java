package com.example.notesapp.service;

import com.example.notesapp.dto.AddNoteDto;
import com.example.notesapp.dto.NoteDto;
import com.example.notesapp.entity.Note;
import com.example.notesapp.entity.User;
import com.example.notesapp.repository.NotesRepository;
import com.example.notesapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {



    private final NotesRepository notesRepository;

    private final UserRepository userRepository;


    @Transactional
    public NoteDto createNoteForUser(AddNoteDto note, Long userId){

        //create new Note in database
       Note newNote =  new Note();
       newNote.setTitle(note.getTitle());
       newNote.setDescription(note.getDescription());

       newNote = notesRepository.save(newNote);

       //find user with passed id
       User newUser = userRepository.findById(userId)
               .orElseThrow(()->
                       new IllegalArgumentException("No user found with id "+userId));

       //set note with the user
       newNote.setUser(newUser);


       return new NoteDto(
               newNote.getId(),
               newNote.getTitle(),
               newNote.getDescription(),
               newNote.getCreateAt().toString());
    }


    @Transactional
    public NoteDto updateNote(NoteDto updateNote){

        Note oldNote = notesRepository.findById(updateNote.getId()).orElseThrow(()->
                    new IllegalArgumentException("No note found with id "+updateNote.getId()));

        oldNote.setTitle(updateNote.getTitle());
        oldNote.setDescription(updateNote.getDescription());

        oldNote = notesRepository.save(oldNote);

       return new NoteDto(
               oldNote.getId(),
               oldNote.getTitle(),
               oldNote.getDescription(),
               oldNote.getCreateAt().toString());
    }

    @Transactional
    public void deleteNote(Long id){

        notesRepository.deleteById(id);
    }

    @Transactional
    public List<NoteDto> findAllNotesByUser(Long id){

        List<Note> notes = notesRepository.findAllNoteByUser(id);

        return  notes.stream()
                .map(note -> new NoteDto(
                        note.getId(),
                        note.getTitle(),
                        note.getDescription(),
                        note.getCreateAt().toString()
                        )
                ).toList();
    }

    @Transactional
    public NoteDto findNoteById(Long id){

        Note note = notesRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("No note found with id "+id));

        return new NoteDto(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.getCreateAt().toString());
    }



}
