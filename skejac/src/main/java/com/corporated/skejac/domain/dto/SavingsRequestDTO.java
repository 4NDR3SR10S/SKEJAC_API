package com.corporated.skejac.domain.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingsRequestDTO {

    // El monto que el usuario desea transferir a ahorros
    private BigDecimal amount;

    // *** NUEVO CAMPO: Identificador del usuario ***
    private String phoneNumber;

    public SavingsRequestDTO() {
    }
}