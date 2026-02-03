package com.example.notesapp.user.controller;

import com.example.notesapp.util.advices.ResponseHandler;
import com.example.notesapp.user.dto.AddUserDto;
import com.example.notesapp.user.dto.UserDto;
import com.example.notesapp.user.service.UserService;
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
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){

        UserDto user  = userService.updateUser(id,userDto);

        return ResponseHandler.responseBuilder(user,HttpStatus.OK,null,LocalDateTime.now());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){

         userService.deleteUser(id);

         var deleterMsg = "User with id "+id+" successfully deleted";

         return ResponseHandler.responseBuilder(deleterMsg,HttpStatus.OK,null,LocalDateTime.now());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){

        UserDto user = userService.getUserById(id);

        return ResponseHandler.responseBuilder(user,
                HttpStatus.OK,null,LocalDateTime.now());
    }


}
