package com.corporated.skejac.domain.services;

import com.corporated.skejac.domain.dto.UserXampleDto;
import com.corporated.skejac.domain.repository.UserXampleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserXampleService {
    private final UserXampleRepository userXampleRepository;

    public UserXampleService(UserXampleRepository userXampleRepository){
        this.userXampleRepository=userXampleRepository;
    }

    //crear usuario de ejemplo
    public UserXampleDto creatUserXample(UserXampleDto userXampleDto){
      return this.userXampleRepository.creatUserXample(userXampleDto);
    }

}
