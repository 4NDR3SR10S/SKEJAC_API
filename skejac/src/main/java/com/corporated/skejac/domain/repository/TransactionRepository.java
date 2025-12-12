package com.corporated.skejac.domain.repository;

import com.corporated.skejac.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    // Busca historial donde soy remitente O destinatario, ordenado por fecha
    List<TransactionEntity> findBySenderPhoneOrRecipientPhoneOrderByTransactionDateDesc(String senderPhone, String recipientPhone);
}