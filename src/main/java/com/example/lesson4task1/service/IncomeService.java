package com.example.lesson4task1.service;

import com.example.lesson4task1.entity.Card;
import com.example.lesson4task1.entity.Income;
import com.example.lesson4task1.payload.CardDto;
import com.example.lesson4task1.payload.IncomeDto;
import com.example.lesson4task1.repository.CardRepository;
import com.example.lesson4task1.repository.IncomeRepository;
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
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    CardRepository cardRepository;


    public HttpEntity<?> getAll() {
        List<Income> all = incomeRepository.findAll();
        return ResponseEntity.ok(all);
    }


    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Income> byId = incomeRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.status(404).body("topilmadi");
    }

    public HttpEntity<?> addIncome(IncomeDto incomeDto) {
        boolean b = cardRepository.existsById(incomeDto.getFromCardID());
        if (!b) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Income income = new Income();
        income.setAmount(incomeDto.getAmount());
        income.setFromCard(cardRepository.getById(incomeDto.getFromCardID()));
        income.setDate(incomeDto.getDate());
        incomeRepository.save(income);
        return ResponseEntity.status(200).body("addad");
    }


    public HttpEntity<?> editIncome(Integer id, IncomeDto incomeDto) {
        boolean b = cardRepository.existsById(incomeDto.getFromCardID());
        Optional<Income> byId = incomeRepository.findById(id);
        if (!b || !byId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Income income = incomeRepository.getById(id);
        income.setAmount(incomeDto.getAmount());
        income.setFromCard(cardRepository.getById(incomeDto.getFromCardID()));
        income.setDate(incomeDto.getDate());
        incomeRepository.save(income);
        return ResponseEntity.status(200).body("edited");
    }

    public HttpEntity<?> deleteIncome(Integer id) {
        boolean b = incomeRepository.existsById(id);
        if (!b) {
            return ResponseEntity.status(404).build();
        }
        incomeRepository.deleteById(id);
        return ResponseEntity.status(200).body("deleted");
    }


}
