package com.example.lesson4task1.service;

import com.example.lesson4task1.entity.Card;
import com.example.lesson4task1.entity.Users;
import com.example.lesson4task1.payload.CardDto;
import com.example.lesson4task1.repository.CardRepository;
import com.example.lesson4task1.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;


    public HttpEntity<?> getAll() {
        List<Users> all = usersRepository.findAll();
        return ResponseEntity.ok(all);
    }


    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.status(404).body("topilmadi");
    }

    public HttpEntity<?> addUser(Users users) {
        boolean b = usersRepository.existsByUsernameAndPassword(users.getUsername(), users.getPassword());
        if (b) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Users users1 = new Users();
        users1.setUsername(users.getUsername());
        users1.setPassword(users.getPassword());
        return ResponseEntity.status(200).body("addad");
    }


    public HttpEntity<?> editUser(Integer id, Users users) {
        boolean b = usersRepository.existsByUsernameAndPasswordAndIdNot(users.getUsername(), users.getPassword(), id);
        if (b) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Users users1 = usersRepository.findById(id).get();
        users1.setUsername(users.getUsername());
        users1.setPassword(users.getPassword());
        return ResponseEntity.status(200).body("edited");
    }

    public HttpEntity<?> deleteUser(Integer id) {
        boolean b = usersRepository.existsById(id);
        if (!b) {
            return ResponseEntity.status(404).build();
        }
        usersRepository.deleteById(id);
        return ResponseEntity.status(200).body("deleted");
    }

}
