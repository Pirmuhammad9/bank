package com.example.lesson4task1.repository;

import com.example.lesson4task1.entity.Card;
import com.example.lesson4task1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByUsernameAndPassword(String username, String password);
    boolean existsByUsernameAndPasswordAndIdNot(String username, String password, Integer id);
}
