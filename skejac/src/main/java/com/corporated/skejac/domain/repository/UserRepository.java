package com.corporated.skejac.domain.repository;

import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<UserEntity> findByPhoneNumberAndRegisteredPin(String phoneNumber, String registeredPin);

}