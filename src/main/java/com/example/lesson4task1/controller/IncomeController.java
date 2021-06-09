package com.example.lesson4task1.controller;

import com.example.lesson4task1.payload.CardDto;
import com.example.lesson4task1.payload.IncomeDto;
import com.example.lesson4task1.service.CardService;
import com.example.lesson4task1.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    @GetMapping
    public HttpEntity<?> getAll(){
        return incomeService.getAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        return incomeService.getOne(id);
    }

    @PostMapping
    public HttpEntity<?> addIncome(@RequestBody IncomeDto incomeDto){
        return incomeService.addIncome(incomeDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editIncome(@PathVariable Integer id, @RequestBody IncomeDto incomeDto){
        return incomeService.editIncome(id, incomeDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteIncome(@PathVariable Integer id){
        return incomeService.deleteIncome(id);
    }


}
