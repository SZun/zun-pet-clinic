package com.zun.petclinic.repositories;

import com.zun.petclinic.models.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
