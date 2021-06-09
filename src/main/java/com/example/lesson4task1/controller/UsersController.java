package com.example.lesson4task1.controller;

import com.example.lesson4task1.entity.Users;
import com.example.lesson4task1.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping
    public HttpEntity<?> getAll(){
        return usersService.getAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        return usersService.getOne(id);
    }

    @PostMapping
    public HttpEntity<?> addUser(@RequestBody Users users){
        return usersService.addUser(users);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable Integer id, @RequestBody Users users){
        return usersService.editUser(id, users);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        return usersService.deleteUser(id);
    }



}
