package com.example.notesapp.user.service;

import com.example.notesapp.user.dto.AddUserDto;
import com.example.notesapp.user.dto.UserDto;
import com.example.notesapp.user.entity.User;
import com.example.notesapp.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserDto createUser(AddUserDto addUserDto){

        User user = new User();

        user.setName(addUserDto.getName());
        user.setEmail(addUserDto.getEmail());
        user.setPassword(addUserDto.getPassword());

        user = userRepository.save(user);

        return  new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword()) ;
    }

    @Transactional
    public UserDto updateUser(Long id,UserDto updateUser){



        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("No user found with id "+id));

        user.setName(updateUser.getName());
        user.setPassword(updateUser.getPassword());

        user = userRepository.save(user);

        return new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword());

    }

    @Transactional
    public void deleteUser(Long id){

        userRepository.deleteById(id);
    }


    public UserDto getUserById(Long id){

        User user =userRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("no user found with id "+id));;

        return new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword());
    }




}
