package com.corporated.skejac.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_phone", nullable = false)
    private String senderPhone;

    @Column(name = "recipient_phone", nullable = false)
    private String recipientPhone;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    public TransactionEntity() {}

    public TransactionEntity(String senderPhone, String recipientPhone, BigDecimal amount, LocalDateTime transactionDate) {
        this.senderPhone = senderPhone;
        this.recipientPhone = recipientPhone;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
}