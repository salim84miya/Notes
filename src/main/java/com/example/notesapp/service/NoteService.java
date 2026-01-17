package com.example.notesapp.service;

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
    public Note createNoteForUser(Note note,Long id){

       Note newNote =  notesRepository.save(note);

       User newUser = userRepository.findById(id)
               .orElseThrow(()->
                       new IllegalArgumentException("No user found with id "+id));

       newNote.setUser(newUser);

       return newNote;
    }


    @Transactional
    public Note updateNote(Note updateNote){

        Note oldNote = notesRepository.findById(updateNote.getId()).orElseThrow(()->
                    new IllegalArgumentException("No note found with id "+updateNote.getId()));

        oldNote.setTitle(updateNote.getTitle());
        oldNote.setDescription(updateNote.getDescription());

       return notesRepository.save(oldNote);
    }

    @Transactional
    public void deleteNote(Long id){

        notesRepository.deleteById(id);
    }

    @Transactional
    public List<Note> findAllNotesByUser(Long id){

        return  notesRepository.findAll();
    }

    @Transactional
    public Note findNoteById(Long id){

        Note note = notesRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("No note found with id "+id));
        return note;
    }



}
