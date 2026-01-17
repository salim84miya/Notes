package com.example.notesapp.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddUserDto {

    private  String name;

    private String email;

    private String password;
}
