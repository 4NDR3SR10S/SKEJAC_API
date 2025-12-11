package com.corporated.skejac.domain.services;

import com.corporated.skejac.domain.dto.LoginRequestDto;
import com.corporated.skejac.domain.dto.UserProfileResponseDto;
import com.corporated.skejac.domain.dto.UserRegisterRequestDto;
import com.corporated.skejac.domain.repository.UserRepository;
import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal; // Importante
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    // MÉTODO DE REGISTRO (NUEVO)
    public boolean registerUser(UserRegisterRequestDto request) {
        if (checkUserExists(request.getPhoneNumber())) {
            return false;
        }

        UserEntity newUser = new UserEntity();
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setFullName(request.getFullName());
        newUser.setDocumentId(request.getDocumentId());
        newUser.setRegisteredPin(request.getPin()); // Mapeamos 'pin' del DTO a 'registeredPin' de la Entidad
        newUser.setCurrentBalance(new BigDecimal("2500000")); // Saldo inicial en 2500000

        userRepository.save(newUser);
        return true;
    }

    public UserProfileResponseDto getUserProfile(String phoneNumber) {

        Optional<UserEntity> userOpt = userRepository.findByPhoneNumber(phoneNumber);

        if (userOpt.isEmpty()) {
            return null;
        }

        UserEntity user = userOpt.get();

        UserProfileResponseDto dto = new UserProfileResponseDto();
        dto.setFullName(user.getFullName());
        dto.setCurrentBalance(user.getCurrentBalance());

        return dto;
    }


}