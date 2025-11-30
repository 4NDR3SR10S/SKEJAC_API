package com.corporated.skejac.persistence.mapper;

import com.corporated.skejac.domain.dto.UserRegisterRequestDto;
import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserRegisterMapper {

    public UserEntity toEntity(UserRegisterRequestDto dto) {
        if (dto == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setFullName(dto.getFullName());
        entity.setDocumentId(dto.getDocumentId());
        entity.setRegisteredPin(dto.getRegisteredPin());
        //Ponerle en cero la cuenta
        entity.setCurrentBalance(BigDecimal.ZERO);

        return entity;
    }
}
