package com.example.notesapp.auth.repository;

import com.example.notesapp.auth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {


    Optional<AppUser> findByUsername(String username);


    Boolean existsByUsername(String username);
}
