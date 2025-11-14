package com.corporated.skejac.domain.services;

import com.corporated.skejac.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkUserExists(String phoneNumber) {

        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    // --- PRÓXIMOS PASOS ---
    // Aquí añadiremos la lógica para RM-29 (Authenticate)
    // y RM-32 (Register User)
}