package com.example.notesapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddNoteDto {

    private String title;

    private String description;
}
