package com.corporated.skejac.domain.repository;

import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Método existente para verificar registro
    boolean existsByPhoneNumber(String phoneNumber);

    // Método existente para login (Teléfono + PIN)
    Optional<UserEntity> findByPhoneNumberAndRegisteredPin(String phoneNumber, String registeredPin);

    // === NUEVO MÉTODO PARA TRANSACCIONES ===
    // Necesario para buscar al remitente y al destinatario solo por su número
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}