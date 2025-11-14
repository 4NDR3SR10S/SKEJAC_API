package com.corporated.skejac.persistence.crud;

import com.corporated.skejac.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudUserXample extends CrudRepository<UserEntity,Integer> {
}
