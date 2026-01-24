package com.example.notesapp.entity;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 40)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(length = 20,nullable = false)
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy ="user",orphanRemoval =  true,cascade = {CascadeType.ALL})
    private List<Note> note = new ArrayList<>();
}
