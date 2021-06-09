package com.example.lesson4task1.service;

import com.example.lesson4task1.entity.Card;
import com.example.lesson4task1.payload.CardDto;
import com.example.lesson4task1.repository.CardRepository;
import com.example.lesson4task1.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    UsersRepository usersRepository;


    public HttpEntity<?> getAll() {
        List<Card> all = cardRepository.findAll();
        return ResponseEntity.ok(all);
    }


    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Card> byId = cardRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.status(404).body("topilmadi");
    }

    public HttpEntity<?> addCard(CardDto cardDto) {
        boolean b = cardRepository.existsByNumber(cardDto.getNumber());
        boolean b1 = cardRepository.existsByUsersId(cardDto.getUserId());
        if (b || !b1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Card card = new Card();
        card.setActive(cardDto.isActive());
        card.setBalance(cardDto.getBalance());
        card.setExpiredDate(card.getExpiredDate());
        card.setNumber(cardDto.getNumber());
        card.setUsers(usersRepository.findById(cardDto.getUserId()).get());
        cardRepository.save(card);
        return ResponseEntity.status(200).body("addad");
    }


    public HttpEntity<?> editCard(Integer id, CardDto cardDto) {
        Optional<Card> byId = cardRepository.findById(id);
        boolean b1 = cardRepository.existsByNumberAndIdNot(cardDto.getNumber(), id);
        boolean b2 = cardRepository.existsByUsersId(cardDto.getUserId());
        if (!byId.isPresent() || b1 || !b2) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Card card = byId.get();
        card.setActive(cardDto.isActive());
        card.setBalance(cardDto.getBalance());
        card.setExpiredDate(card.getExpiredDate());
        card.setNumber(cardDto.getNumber());
        card.setUsers(usersRepository.findById(cardDto.getUserId()).get());
        cardRepository.save(card);
        return ResponseEntity.status(200).body("edited");
    }

    public HttpEntity<?> deleteCard(Integer id) {
        boolean b = cardRepository.existsById(id);
        if (!b) {
            return ResponseEntity.status(404).build();
        }
        cardRepository.deleteById(id);
        return ResponseEntity.status(200).body("deleted");
    }

}
