package com.corporated.skejac.domain.services;

import com.corporated.skejac.domain.dto.TransactionHistoryDto;
import com.corporated.skejac.domain.dto.TransactionRequestDto;
import com.corporated.skejac.domain.repository.TransactionRepository;
import com.corporated.skejac.domain.repository.UserRepository;
import com.corporated.skejac.persistence.entity.TransactionEntity;
import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public boolean transferMoney(TransactionRequestDto request) {
        // 1. Validar remitente
        Optional<UserEntity> senderOpt = userRepository.findByPhoneNumber(request.getSenderPhone());
        if (senderOpt.isEmpty()) throw new RuntimeException("USER_NOT_FOUND");
        UserEntity sender = senderOpt.get();

        // 2. Validaciones básicas
        if (sender.getPhoneNumber().equals(request.getRecipientPhone())) throw new RuntimeException("SELF_TRANSFER_NOT_ALLOWED");
        if (sender.getCurrentBalance().compareTo(request.getAmount()) < 0) throw new RuntimeException("INSUFFICIENT_FUNDS");

        // 3. MOVER DINERO (Solo descontamos al sender, destinatario opcional)
        sender.setCurrentBalance(sender.getCurrentBalance().subtract(request.getAmount()));
        userRepository.save(sender);

        Optional<UserEntity> recipientOpt = userRepository.findByPhoneNumber(request.getRecipientPhone());
        if (recipientOpt.isPresent()) {
            UserEntity recipient = recipientOpt.get();
            recipient.setCurrentBalance(recipient.getCurrentBalance().add(request.getAmount()));
            userRepository.save(recipient);
        }

        // 4. *** REGISTRAR EL MOVIMIENTO (ESTO ES LO QUE FALTABA) ***
        TransactionEntity transaction = new TransactionEntity(
                sender.getPhoneNumber(),
                request.getRecipientPhone(),
                request.getAmount(),
                LocalDateTime.now()
        );
        transactionRepository.save(transaction); // Guardado en BD

        return true;
    }

    // Obtener Historial
    public List<TransactionHistoryDto> getTransactionHistory(String myPhoneNumber) {
        List<TransactionEntity> transactions = transactionRepository
                .findBySenderPhoneOrRecipientPhoneOrderByTransactionDateDesc(myPhoneNumber, myPhoneNumber);

        return transactions.stream().map(tx -> {
            boolean isExpense = tx.getSenderPhone().equals(myPhoneNumber);
            String title = isExpense ? "Envío a " + tx.getRecipientPhone() : "Recibido de " + tx.getSenderPhone();
            String type = isExpense ? "expense" : "income";
            String date = tx.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Fecha simple

            return new TransactionHistoryDto(title, tx.getAmount(), type, date);
        }).collect(Collectors.toList());
    }
}