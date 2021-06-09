package com.example.lesson4task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User("pdp", passwordEncoder.encode("pdpUz"), new ArrayList<>()),
                        new User("ecma", passwordEncoder.encode("ecmaUz"), new ArrayList<>()),
                        new User("aif", passwordEncoder.encode("aifUz"), new ArrayList<>())
                )
        );
        for (User user : users) {
            if (user.getUsername().equals(s)){
                return user;
            }
        }
        throw new UsernameNotFoundException("User topilmadi");
    }
}
