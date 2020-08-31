package com.zun.petclinic.repositories;

import com.zun.petclinic.models.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
