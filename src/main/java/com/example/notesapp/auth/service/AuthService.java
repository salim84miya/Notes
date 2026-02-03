package com.example.notesapp.auth.service;

import com.example.notesapp.auth.dto.*;
import com.example.notesapp.auth.entity.AppUser;
import com.example.notesapp.auth.repository.AppUserRepository;
import com.example.notesapp.auth.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto loginUser(LoginDto userDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword())
        );

        AppUser user = (AppUser) authentication.getPrincipal();

        String token = authUtil.createAccessToken(user);

        return new LoginResponseDto(token,user.getId());
    }

    public  SignupResponseDto signUp(SignupDto userDto) {

        Boolean isExists = appUserRepository.existsByUsername(userDto.getUsername());

        if(isExists){
            throw new  IllegalArgumentException("User Already Exists!");
        }


      AppUser user = AppUser.builder()
              .username(userDto.getUsername())
              .password(passwordEncoder.encode(userDto.getPassword()))
                      .build();

      user = appUserRepository.save(user);

      return SignupResponseDto.builder()
              .id(user.getId())
              .username(user.getUsername())
              .password(user.getPassword())
              .build();

    }
}
