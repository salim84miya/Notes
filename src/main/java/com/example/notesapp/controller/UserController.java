package com.example.notesapp.controller;

import com.example.notesapp.advices.ResponseHandler;
import com.example.notesapp.dto.AddUserDto;
import com.example.notesapp.dto.UserDto;
import com.example.notesapp.entity.User;
import com.example.notesapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody @Valid AddUserDto addUserDto){

        UserDto user =  userService.createUser(addUserDto);

        return ResponseHandler.responseBuilder(user,
                HttpStatus.OK,null, LocalDateTime.now());
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){

        return userService.updateUser(id,userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){

         userService.deleteUser(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){

        User user = userService.getUserById(id).orElseThrow(()->
                new IllegalArgumentException("no user found with id"+id));

        return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
    }


}
