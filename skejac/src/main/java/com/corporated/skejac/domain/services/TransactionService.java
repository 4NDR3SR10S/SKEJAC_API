package com.corporated.skejac.domain.services;

import com.corporated.skejac.domain.dto.TransactionRequestDto;
import com.corporated.skejac.domain.repository.UserRepository;
import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    // @Transactional asegura que si falla el descuento o el abono, se revierta todo.
    @Transactional
    public boolean transferMoney(TransactionRequestDto request) {

        // 1. Buscar al remitente (Sender)
        Optional<UserEntity> senderOpt = userRepository.findByPhoneNumber(request.getSenderPhone());

        // 2. Buscar al destinatario (Recipient)
        Optional<UserEntity> recipientOpt = userRepository.findByPhoneNumber(request.getRecipientPhone());

        // Validación: Ambos deben existir
        if (senderOpt.isEmpty() || recipientOpt.isEmpty()) {
            throw new RuntimeException("USER_NOT_FOUND");
        }

        UserEntity sender = senderOpt.get();
        UserEntity recipient = recipientOpt.get();

        // Evitar transferirse a sí mismo (opcional pero recomendado)
        if (sender.getPhoneNumber().equals(recipient.getPhoneNumber())) {
            throw new RuntimeException("SELF_TRANSFER_NOT_ALLOWED");
        }

        // 3. Verificar saldo suficiente
        // compareTo devuelve -1 si es menor, 0 si es igual, 1 si es mayor
        if (sender.getCurrentBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("INSUFFICIENT_FUNDS");
        }

        // 4. Ejecutar la transferencia
        // Restar al remitente
        sender.setCurrentBalance(sender.getCurrentBalance().subtract(request.getAmount()));
        // Sumar al destinatario
        recipient.setCurrentBalance(recipient.getCurrentBalance().add(request.getAmount()));

        // 5. Guardar los cambios en BD
        userRepository.save(sender);
        userRepository.save(recipient);

        return true;
    }
}