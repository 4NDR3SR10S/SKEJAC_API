package com.corporated.skejac.domain.dto;

import com.corporated.skejac.persistence.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SavingsResponseDTO {

    // Saldo principal actualizado
    private BigDecimal currentBalance;

    // Saldo de ahorros actualizado
    private BigDecimal savingsBalance;

    public SavingsResponseDTO() {
    }

    // Constructor de mapeo (Usado por el Controller para crear la respuesta a partir de la Entity)
    public SavingsResponseDTO(UserEntity entity) {
        this.currentBalance = entity.getCurrentBalance();
        this.savingsBalance = entity.getSavingsBalance();
    }
}