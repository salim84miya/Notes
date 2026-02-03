package com.example.notesapp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddUserDto {

    @NotBlank(message = "Name is required")
    @Size(min =3 ,max = 15,message = "Name should be 3 to 15 character long")
    private  String name;

    @NotBlank(message = "Email is  required")
    @Email(message = "Enter correct Email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8,max = 20,message = "password should be 8 to 20 character long")
    private String password;
}
