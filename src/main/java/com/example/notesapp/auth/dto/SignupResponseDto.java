package com.example.notesapp.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponseDto {

    private Long id;
    private String username;
    private String password;
}
