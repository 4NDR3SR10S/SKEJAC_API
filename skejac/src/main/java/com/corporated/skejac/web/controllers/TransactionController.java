package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.TransactionRequestDto;
import com.corporated.skejac.domain.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "*") // Permite peticiones desde tu Frontend JS
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMoney(@RequestBody TransactionRequestDto request) {
        try {
            boolean success = transactionService.transferMoney(request);

            if (success) {
                return new ResponseEntity<>("Transferencia exitosa", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error desconocido al procesar la transacción", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (RuntimeException e) {
            // Manejo de errores controlados por el Service
            String msg = e.getMessage();

            if ("USER_NOT_FOUND".equals(msg)) {
                return new ResponseEntity<>("El usuario destino (o remitente) no existe", HttpStatus.NOT_FOUND);
            }
            if ("INSUFFICIENT_FUNDS".equals(msg)) {
                return new ResponseEntity<>("Fondos insuficientes", HttpStatus.BAD_REQUEST);
            }
            if ("SELF_TRANSFER_NOT_ALLOWED".equals(msg)) {
                return new ResponseEntity<>("No puedes enviarte dinero a ti mismo", HttpStatus.BAD_REQUEST);
            }

            // Error no controlado
            return new ResponseEntity<>("Error interno: " + msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}