package com.example.notesapp;

import com.example.notesapp.dto.AddUserDto;
import com.example.notesapp.dto.UserDto;
import com.example.notesapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;



    @Test
    public void createUserTest(){

        AddUserDto addUserDto = AddUserDto.builder()
                .email("salimgmail.com")
                .password("salim123456")
                .name("salim")
                .build();

        UserDto newUser = userService.createUser(addUserDto);

        System.out.println(newUser);


    }

    @Test
    public void updateUser(){

        UserDto user = UserDto.builder()
                .id(2L)
                .password("salim123456")
                .name("salim")
                .build();


       UserDto updatedUser = userService.updateUser(2L,user);

       System.out.println(updatedUser);
    }

    @Test
    public void deleteUser(){

        userService.deleteUser(1L);
    }
}
