package com.corporated.skejac.domain.repository;

import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserEntity, Long> {

    //Método personalizado para corroborar existencias de números
    boolean existsByPhoneNumber(String phoneNumber);

}
