package com.example.lesson4task1.controller;


import com.example.lesson4task1.payload.LoginDto;
import com.example.lesson4task1.security.JwtProvider;
import com.example.lesson4task1.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    MyAuthService myAuthService;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public HttpEntity<?> logIntoSystem(@RequestBody LoginDto loginDto) {
        try {
            //   UserDetails userDetails = myAuthService.loadUserByUsername(loginDto.getUsername());
            //   boolean equals = userDetails.getPassword().equals(loginDto.getPassword());
            //  boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword());
            //   if (matches) {
            //  }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            String token = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(token);
        }catch (BadCredentialsException exception){
            return ResponseEntity.status(401).body("login yoki parol xato");
        }

    }

}
