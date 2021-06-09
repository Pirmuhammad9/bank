package com.example.lesson4task1.controller;


import com.example.lesson4task1.payload.CardDto;
import com.example.lesson4task1.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping
    public HttpEntity<?> getAll(){
        return cardService.getAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        return cardService.getOne(id);
    }

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody CardDto cardDto){
      return cardService.addCard(cardDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCard(@PathVariable Integer id, @RequestBody CardDto cardDto){
        return cardService.editCard(id, cardDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCard(@PathVariable Integer id){
        return cardService.deleteCard(id);
    }

}
