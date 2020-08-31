package com.zun.petclinic.repositories;

import com.zun.petclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
