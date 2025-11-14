package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.UserRequestDto;
import com.corporated.skejac.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador API para manejar todos los flujos de Autenticación y Registro.
 */
@RestController
@RequestMapping("/api/auth") // Todas las rutas aquí empiezan con /api/auth
@CrossOrigin(origins = "*")
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

    // --- PRÓXIMOS PASOS ---
    // Aquí añadiremos los endpoints para RM-29 (Login) y RM-32 (Register)
}