package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.LoginRequestDto;
import com.corporated.skejac.domain.dto.UserRegisterRequestDto; // Usamos tu DTO
import com.corporated.skejac.domain.dto.UserRequestDto;
import com.corporated.skejac.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Ruta base unificada
@CrossOrigin(origins = "*") // Permite conexión desde cualquier origen (Frontend)
public class AuthController {

    @Autowired
    private UserService userService;

    // 1. Verificar Número (Existente)
    @PostMapping("/check-number")
    public ResponseEntity<?> checkUserExistence(@RequestBody UserRequestDto request) {
        boolean userExists = userService.checkUserExists(request.getPhoneNumber());
        return userExists ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 2. Login (Existente)
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDto loginRequest) {
        boolean isAuthenticated = userService.authenticateUser(loginRequest);
        return isAuthenticated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    // 3. REGISTRO (US RM-32)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDto request) {
        try {
            boolean isRegistered = userService.registerUser(request);
            if (isRegistered) {
                return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("El usuario ya existe", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error interno: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}