package com.example.notesapp.auth.security;

import com.example.notesapp.auth.entity.AppUser;
import com.example.notesapp.auth.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil;
    private final AppUserRepository appUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        log.info("request url: {}",request.getRequestURI());

       final String requestTokenHeader = request.getHeader("Authorization");

       log.info(requestTokenHeader);

        if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer") ){

            filterChain.doFilter(request,response);
            return;
        }

        String token = requestTokenHeader.split("Bearer ")[1];
        String username =  authUtil.findUsernameByToken(token);

        log.info(username);

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            AppUser user = appUserRepository.findByUsername(username).orElseThrow();

            log.info("user {}",user);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }


        filterChain.doFilter(request,response);
    }
}
