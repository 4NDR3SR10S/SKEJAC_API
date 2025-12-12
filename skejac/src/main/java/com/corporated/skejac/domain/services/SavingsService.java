package com.corporated.skejac.domain.services;

import com.corporated.skejac.persistence.entity.UserEntity;
import com.corporated.skejac.domain.repository.SavingsRepository;
import com.corporated.skejac.domain.dto.SavingsRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // O jakarta.transaction.Transactional
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SavingsService {

    private final SavingsRepository savingsRepository;

    public SavingsService(SavingsRepository savingsRepository) {
        this.savingsRepository = savingsRepository;
    }

    /**
     * Traslada el monto especificado del saldo principal al saldo de ahorros del usuario identificado por el phoneNumber.
     * * @param requestDTO Contiene el monto a ahorrar y el phoneNumber.
     * @return UserEntity actualizada.
     */
    @Transactional
    public UserEntity saveMoney(SavingsRequestDTO requestDTO) {

        BigDecimal amountToSave = requestDTO.getAmount();
        String phoneNumber = requestDTO.getPhoneNumber(); // <--- OBTENEMOS EL TELÉFONO DEL DTO

        // 1. Obtener el usuario por PhoneNumber
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede ser nulo.");
        }

        UserEntity user = savingsRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con número: " + phoneNumber));

        // 2. Validaciones

        // 2.1. Validar que el monto no sea nulo y sea positivo
        if (amountToSave == null || amountToSave.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a ahorrar debe ser un valor positivo.");
        }

        // 2.2. Asegurar la precisión
        amountToSave = amountToSave.setScale(2, RoundingMode.HALF_UP);

        // 2.3. Validar saldo suficiente
        if (user.getCurrentBalance().compareTo(amountToSave) < 0) {
            throw new IllegalArgumentException("Saldo principal insuficiente para realizar el ahorro. Saldo actual: " + user.getCurrentBalance());
        }

        // 3. Actualizar Saldos

        // Descontar del saldo principal
        BigDecimal newCurrentBalance = user.getCurrentBalance().subtract(amountToSave);
        user.setCurrentBalance(newCurrentBalance);

        // Añadir al saldo de ahorros
        BigDecimal newSavingsBalance = user.getSavingsBalance().add(amountToSave);
        user.setSavingsBalance(newSavingsBalance);

        // 4. Guardar y retornar
        return savingsRepository.save(user);
    }

    /**
     * Obtiene la información de balances del usuario identificado por el phoneNumber.
     */
    public UserEntity getBalanceInfo(String phoneNumber) {
        return savingsRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }
}