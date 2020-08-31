package com.zun.petclinic.services;

import com.zun.petclinic.models.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String LastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();

}
