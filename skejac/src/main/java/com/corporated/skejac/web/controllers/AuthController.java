package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.LoginRequestDto; // 1. Importa el DTO de Login (NUEVO)
import com.corporated.skejac.domain.dto.UserRequestDto; // El DTO de la US RM-28
import com.corporated.skejac.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth") // Todas las rutas aquí empiezan con /api/auth
@CrossOrigin(origins = "*") // Permite la conexión con el Frontend
public class AuthController {

    @Autowired
    private UserService userService; // Inyectamos el Servicio
    @PostMapping("/check-number")
    public ResponseEntity<?> checkUserExistence(@RequestBody UserRequestDto request) {

        boolean userExists = userService.checkUserExists(request.getPhoneNumber());

        if (userExists) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDto loginRequest) {
        boolean isAuthenticated = userService.authenticateUser(loginRequest);

        if (isAuthenticated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // --- PRÓXIMOS PASOS ---
    // Aquí añadiremos el endpoint para RM-32 (Register)
}