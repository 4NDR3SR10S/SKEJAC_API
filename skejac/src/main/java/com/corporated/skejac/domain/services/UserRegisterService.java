package com.corporated.skejac.domain.services;

import com.corporated.skejac.domain.dto.UserRegisterRequestDto;
import com.corporated.skejac.persistence.mapper.UserRegisterMapper;
import com.corporated.skejac.domain.repository.UserRegisterRepository;
import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    private final UserRegisterRepository userRegisterRepository;
    private final UserRegisterMapper userRegisterMapper;

    public UserRegisterService(UserRegisterRepository userRegisterRepository,
                               UserRegisterMapper userRegisterMapper) {
        this.userRegisterRepository = userRegisterRepository;
        this.userRegisterMapper = userRegisterMapper;
    }

    public UserEntity registerNewUser(UserRegisterRequestDto request) {

        if (userRegisterRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("PHONE_ALREADY_EXISTS");
        }

        UserEntity newUser = userRegisterMapper.toEntity(request);

        return userRegisterRepository.save(newUser);
    }
}
