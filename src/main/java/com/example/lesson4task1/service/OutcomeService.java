package com.example.lesson4task1.service;

import com.example.lesson4task1.entity.Card;
import com.example.lesson4task1.entity.Income;
import com.example.lesson4task1.entity.Outcome;
import com.example.lesson4task1.payload.IncomeDto;
import com.example.lesson4task1.payload.OutcomeDto;
import com.example.lesson4task1.repository.CardRepository;
import com.example.lesson4task1.repository.IncomeRepository;
import com.example.lesson4task1.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    CardRepository cardRepository;


    public HttpEntity<?> getAll() {
        List<Outcome> all = outcomeRepository.findAll();
        return ResponseEntity.ok(all);
    }


    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Outcome> byId = outcomeRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.status(404).body("topilmadi");
    }

    public HttpEntity<?> addOutcome(OutcomeDto outcomeDto) {
        Optional<Card> byId = cardRepository.findById(outcomeDto.getToCardId());
        Optional<Card> byId1 = cardRepository.findById(outcomeDto.getFromCardID());
        if (!byId.isPresent() || !byId1.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Outcome outcome = new Outcome();
        outcome.setAmount(outcomeDto.getAmount());
        outcome.setDate(outcomeDto.getDate());
        outcome.setComissionAmount(outcomeDto.getComissionAmount());
        outcome.setToCard(byId.get());
        outcome.setFromCard(byId1.get());
        return ResponseEntity.status(200).body("addad");
    }


    public HttpEntity<?> editOutcome(Integer id, OutcomeDto outcomeDto) {
        Optional<Card> byId = cardRepository.findById(outcomeDto.getToCardId());
        Optional<Card> byId2 = cardRepository.findById(outcomeDto.getFromCardID());
        Optional<Outcome> byId1 = outcomeRepository.findById(id);
        if (!byId.isPresent() || !byId1.isPresent() || !byId2.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Outcome outcome = byId1.get();
        outcome.setAmount(outcomeDto.getAmount());
        outcome.setDate(outcomeDto.getDate());
        outcome.setComissionAmount(outcomeDto.getComissionAmount());
        outcome.setToCard(byId.get());
        outcome.setFromCard(byId2.get());
        return ResponseEntity.status(200).body("edited");
    }

    public HttpEntity<?> deleteOutcome(Integer id) {
        boolean b = outcomeRepository.existsById(id);
        if (!b) {
            return ResponseEntity.status(404).build();
        }
        outcomeRepository.deleteById(id);
        return ResponseEntity.status(200).body("deleted");
    }



}
