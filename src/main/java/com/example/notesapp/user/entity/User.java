package com.example.notesapp.user.entity;

import com.example.notesapp.auth.entity.AppUser;
import com.example.notesapp.notes.entity.Note;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 40)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy ="user",orphanRemoval =  true,cascade = {CascadeType.ALL})
    private List<Note> note = new ArrayList<>();

    @OneToOne
    private AppUser appUser;

}
