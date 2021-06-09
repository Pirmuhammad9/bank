package com.example.lesson4task1.repository;

import com.example.lesson4task1.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String number, Integer id);

    boolean existsByUsersId(Integer users_id);

}
