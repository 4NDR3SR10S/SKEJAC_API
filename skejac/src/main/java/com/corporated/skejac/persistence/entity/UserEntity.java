package com.corporated.skejac.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", unique = true, nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "document_id", nullable = false, length = 20)
    private String documentId;

    @Column(name = "registered_pin", nullable = false, length = 4)
    private String registeredPin;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    public UserEntity() {
    }

}