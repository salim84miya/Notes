package com.example.notesapp;

import com.example.notesapp.entity.User;
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

        User user = User.builder()
                .email("salim@gmail.com")
                .password("salim123456")
                .name("salim")
                .build();

        User newUser = userService.createUser(user);

        System.out.println(newUser);


    }

    @Test
    public void updateUser(){

        User user = User.builder()
                .id(2L)
                .password("salim123456")
                .name("salim")
                .build();


       User updatedUser = userService.updateUser(user);

       System.out.println(updatedUser);
    }

    @Test
    public void deleteUser(){

        userService.deleteUser(1L);
    }
}
