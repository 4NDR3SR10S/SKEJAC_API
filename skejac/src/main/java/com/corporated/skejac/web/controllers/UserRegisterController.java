package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.UserRegisterRequestDto;
import com.corporated.skejac.domain.services.UserRegisterService;
import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    public UserRegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserRegisterRequestDto request) {

        try {
            UserEntity createdUser = userRegisterService.registerNewUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

        } catch (RuntimeException e) {

            if ("PHONE_ALREADY_EXISTS".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Phone number already registered");
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error");
        }
    }
}
