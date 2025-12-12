package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.TransactionHistoryDto;
import com.corporated.skejac.domain.dto.TransactionRequestDto;
import com.corporated.skejac.domain.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMoney(@RequestBody TransactionRequestDto request) {
        try {
            boolean success = transactionService.transferMoney(request);
            if (success) return new ResponseEntity<>("Transferencia exitosa", HttpStatus.OK);
            else return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/history")
    public ResponseEntity<?> getHistory(@RequestBody TransactionRequestDto request) {
        try {
            List<TransactionHistoryDto> history = transactionService.getTransactionHistory(request.getSenderPhone());
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener historial", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}