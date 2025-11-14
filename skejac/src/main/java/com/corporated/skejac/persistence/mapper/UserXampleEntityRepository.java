package com.corporated.skejac.persistence.mapper;

import com.corporated.skejac.domain.dto.UserXampleDto;
import com.corporated.skejac.domain.repository.UserXampleRepository;
import com.corporated.skejac.persistence.crud.CrudUserXample;
import com.corporated.skejac.persistence.entity.UserXampleEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserXampleEntityRepository implements UserXampleRepository {

    private final CrudUserXample crudUserXample;
    private final UserXampleMapper userXampleMapper;

    public UserXampleEntityRepository(CrudUserXample crudUserXample,UserXampleMapper userXampleMapper){
        this.crudUserXample =crudUserXample;
        this.userXampleMapper=userXampleMapper;
    }

    @Override
    public UserXampleDto creatUserXample(UserXampleDto userXampleDto) {

        UserXampleEntity entity = userXampleMapper.toUserXampleEntity(userXampleDto);
        return userXampleMapper.toUserXampleDto(crudUserXample.save(entity)) ;
    }
}
