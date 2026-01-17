package com.example.notesapp.service;

import com.example.notesapp.entity.User;
import com.example.notesapp.repository.NotesRepository;
import com.example.notesapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private  final NotesRepository notesRepository;


    public User createUser(User user){

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User updateUser){

        Long id = updateUser.getId();

        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("No user found with id "+id));

        user.setName(updateUser.getName());
        user.setPassword(updateUser.getPassword());

        return userRepository.save(user);

    }

    @Transactional
    public void deleteUser(Long id){

        userRepository.deleteById(id);
    }




}
