package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.UserProfileResponseDto;
import com.corporated.skejac.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestParam String phone) {

        UserProfileResponseDto profile = userService.getUserProfile(phone);

        if (profile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        return ResponseEntity.ok(profile);
    }
}

