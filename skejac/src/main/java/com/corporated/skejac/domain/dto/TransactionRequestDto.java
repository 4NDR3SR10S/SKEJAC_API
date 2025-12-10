package com.corporated.skejac.domain.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionRequestDto {
    private String senderPhone;    // Quién envía (lo sacamos del sessionStorage en el front)
    private String recipientPhone; // A quién envía (input del formulario)
    private BigDecimal amount;     // Cuánto envía
}