package com.corporated.skejac.persistence.mapper;


import com.corporated.skejac.domain.dto.UserXampleDto;
import com.corporated.skejac.persistence.entity.UserXampleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserXampleMapper {

    @Mapping(target = "id_user", source = "idUser")
    @Mapping(target = "frst_name", source ="frstName")
    @Mapping(target = "lst_name", source = "lstName")
    @Mapping(target = "email", source ="email")
    @Mapping(target = "ammount", source = "ammount")
    UserXampleDto toUserXampleDto(UserXampleEntity userXampleEntity);

    List<UserXampleDto> toUserXampleDtos(Iterable<UserXampleEntity> entities);

  @InheritInverseConfiguration
   UserXampleEntity toUserXampleEntity(UserXampleDto dto);
}
