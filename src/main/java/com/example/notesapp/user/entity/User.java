package com.example.notesapp.user.entity;

import com.example.notesapp.notes.entity.Note;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return name;
    }
}
