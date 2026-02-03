package com.example.notesapp.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    private Long id;

    private String title;

    private String description;

    private String createdAt;
}
