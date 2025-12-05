package com.corporated.skejac.domain.dto;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String phoneNumber;
    private String fullName;
    private String documentId; // Coincide con el JS
    private String pin;        // Simplificado de 'registeredPin' a 'pin'
}