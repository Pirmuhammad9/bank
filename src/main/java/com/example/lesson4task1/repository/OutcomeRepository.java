package com.example.lesson4task1.repository;

import com.example.lesson4task1.entity.Card;
import com.example.lesson4task1.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {
}
