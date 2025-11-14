package com.corporated.skejac.persistence.crud;

import com.corporated.skejac.persistence.entity.UserXampleEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudUserXample extends CrudRepository<UserXampleEntity,Integer> {
}
