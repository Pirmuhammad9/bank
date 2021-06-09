package com.example.lesson4task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    private Integer userId;

    private String number;

    private Double balance;

    private Date expiredDate;

    private boolean active;

}
