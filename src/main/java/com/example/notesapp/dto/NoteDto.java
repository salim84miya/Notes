package com.example.notesapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoteDto {

    private Long id;

    private String title;

    private String description;

    private String createdAt;
}
