package com.example.notesapp.repository;

import com.example.notesapp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note,Long> {


    @NativeQuery(value ="SELECT * FROM note where user_id = ?1")
    List<Note> findAllNoteByUser(Long id);


}
