package com.corporated.skejac.domain.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionHistoryDto {
    private String title;
    private BigDecimal amount;
    private String type; // "expense" o "income"
    private String date;

    public TransactionHistoryDto(String title, BigDecimal amount, String type, String date) {
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }
}