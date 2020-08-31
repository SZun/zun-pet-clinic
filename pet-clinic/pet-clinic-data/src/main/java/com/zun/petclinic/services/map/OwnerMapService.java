package com.zun.petclinic.services.map;

import com.zun.petclinic.models.Owner;
import com.zun.petclinic.models.Pet;
import com.zun.petclinic.services.OwnerService;
import com.zun.petclinic.services.PetService;
import com.zun.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        // not my idea
        if(object != null){
            if(object.getPets() != null) {
                object.getPets().forEach(i -> {
                    if(i.getPetType() != null){
                        if(i.getPetType().getId() != null){
                            i.setPetType(petTypeService.save(i.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type Is Required");
                    }
                    if(i.getId() == null){
                        Pet savedPet = petService.save(i);
                        i.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else return null;

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String LastName) {
        return null;
    }
}
