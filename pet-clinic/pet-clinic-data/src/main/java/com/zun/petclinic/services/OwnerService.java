package com.zun.petclinic.services;

import com.zun.petclinic.models.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String LastName);

}
