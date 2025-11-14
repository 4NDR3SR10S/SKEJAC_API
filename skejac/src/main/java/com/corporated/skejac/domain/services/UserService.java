package com.corporated.skejac.domain.services;

import com.corporated.skejac.domain.dto.LoginRequestDto;
import com.corporated.skejac.domain.repository.UserRepository;
import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Inyecta el repositorio
    public boolean checkUserExists(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean authenticateUser(LoginRequestDto loginRequest) {
        Optional<UserEntity> user = userRepository.findByPhoneNumberAndRegisteredPin(
                loginRequest.getPhoneNumber(),
                loginRequest.getPinCode()
        );
        return user.isPresent();
    }
}