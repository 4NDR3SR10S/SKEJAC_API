package com.corporated.skejac.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private String phoneNumber;
    private String pinCode; // El PIN de 4 dígitos

}