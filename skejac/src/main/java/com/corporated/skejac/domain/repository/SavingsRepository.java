package com.corporated.skejac.domain.repository;

import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface SavingsRepository extends JpaRepository<UserEntity, Long> {

    // Método para buscar al usuario por su ID (si es necesario)
    Optional<UserEntity> findById(Long id);

    // *** CLAVE: Método para buscar al usuario por su número de teléfono ***
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}