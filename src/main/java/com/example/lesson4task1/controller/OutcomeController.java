package com.example.lesson4task1.controller;


import com.example.lesson4task1.payload.OutcomeDto;
import com.example.lesson4task1.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {
    @Autowired
    OutcomeService outcomeService;

    @GetMapping
    public HttpEntity<?> getAll(){
        return outcomeService.getAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        return outcomeService.getOne(id);
    }

    @PostMapping
    public HttpEntity<?> addOutcome(@RequestBody OutcomeDto outcomeDto){
        return outcomeService.addOutcome(outcomeDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editOutcome(@PathVariable Integer id, @RequestBody OutcomeDto outcomeDto){
        return outcomeService.editOutcome(id, outcomeDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOutcome(@PathVariable Integer id){
        return outcomeService.deleteOutcome(id);
    }


}
