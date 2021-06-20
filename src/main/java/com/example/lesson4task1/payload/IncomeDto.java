package com.example.lesson4task1.payload;

import com.example.lesson4task1.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDto {

    private Integer fromCardID;

    private Integer toCardID;

    private Double amount;

    private Date date;

}
