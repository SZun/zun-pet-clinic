package com.zun.petclinic.services.springdatajpa;

import com.zun.petclinic.models.Owner;
import com.zun.petclinic.repositories.OwnerRepository;
import com.zun.petclinic.repositories.PetRepository;
import com.zun.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerSDJpaService ownerSDJpaService;

    final String LAST_NAME = "Smith";

    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwner);
        Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(anyString());
    }

    @Test
    void findAll() {
        Set<Owner> expectedOwners = new HashSet<>();
        Stream.of(
                Owner.builder().id(1L).build(),
                Owner.builder().id(2L).build()
        ).forEach(expectedOwners::add);

        when(ownerRepository.findAll()).thenReturn(expectedOwners);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner fromService = ownerSDJpaService.findById(1L);

        assertNotNull(fromService);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner fromService = ownerSDJpaService.findById(1L);

        assertNull(fromService);
    }

    @Test
    void save() {
        Owner toSave = Owner.builder().id(1L).lastName(LAST_NAME).build();

        when(ownerRepository.save(any(Owner.class))).thenReturn(returnOwner);

        Owner fromService = ownerSDJpaService.save(toSave);

        assertNotNull(fromService);

        verify(ownerRepository).save(any(Owner.class));
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);

        verify(ownerRepository, times(1)).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}