package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.SavingsRequestDTO;
import com.corporated.skejac.domain.dto.SavingsResponseDTO;
import com.corporated.skejac.domain.services.SavingsService; // <--- Importación correcta del Servicio
import com.corporated.skejac.persistence.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/savings")
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:5502"})
public class SavingsController {

    private final SavingsService savingsService;

    @Autowired
    public SavingsController(SavingsService savingsService) {
        this.savingsService = savingsService;
    }

    /**
     * Endpoint POST: Realiza la transferencia del saldo principal a ahorros.
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveMoney(@RequestBody SavingsRequestDTO requestDTO) {
        try {
            // 1. Validar que la solicitud contenga el número de teléfono
            if (requestDTO.getPhoneNumber() == null || requestDTO.getPhoneNumber().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El número de teléfono es requerido para identificar la cuenta.");
            }

            // 2. Llamar al servicio con el DTO (usando la instancia correcta)
            UserEntity updatedUser = savingsService.saveMoney(requestDTO);

            // 3. Crear DTO de respuesta con saldos actualizados
            SavingsResponseDTO responseDTO = new SavingsResponseDTO(updatedUser);

            return ResponseEntity.ok(responseDTO);

        } catch (IllegalArgumentException e) {
            // ...
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            // ...
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint GET: Carga la información de saldos para la vista.
     */
    @GetMapping("/balances")
    public ResponseEntity<SavingsResponseDTO> getBalances(@RequestParam String phoneNumber) {
        try {
            if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // Llamar al servicio con el phoneNumber (usando la instancia correcta)
            UserEntity userEntity = savingsService.getBalanceInfo(phoneNumber);
            SavingsResponseDTO responseDTO = new SavingsResponseDTO(userEntity);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}