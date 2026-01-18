package com.example.notesapp.controller;

import com.example.notesapp.dto.AddUserDto;
import com.example.notesapp.dto.UserDto;
import com.example.notesapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public UserDto createUser(@RequestBody AddUserDto addUserDto){

        return userService.createUser(addUserDto);

    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){

        return userService.updateUser(id,userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){

         userService.deleteUser(id);
    }
}
