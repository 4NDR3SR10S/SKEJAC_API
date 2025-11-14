package com.corporated.skejac.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Esta clase @Entity representa la tabla 'users' en la base de datos.
 * Cumple con la US [DB] RM-26.
 * Hibernate usará esto para crear la tabla automáticamente (ddl-auto=update).
 */
@Data // Lombok para generar getters, setters, toString, etc.
@Entity
@Table(name = "users") // Le decimos a Hibernate que la tabla se llama "users"
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cumple AC "phone_number (Unique)"
    @Column(name = "phone_number", unique = true, nullable = false, length = 15)
    private String phoneNumber;

    // Cumple AC "full_name"
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    // Cumple AC "document_id"
    @Column(name = "document_id", nullable = false, length = 20)
    private String documentId;

    // Cumple AC "registered_pin (4 digits)"
    // Guardado como String simple para el proyecto académico
    @Column(name = "registered_pin", nullable = false, length = 4)
    private String registeredPin;

    // Cumple AC "current_balance (DECIMAL, default 0.00)"
    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    // Constructor predeterminado (necesario para JPA)
    public UserEntity() {
    }

    // (Lombok @Data se encarga de todos los Getters y Setters)
}