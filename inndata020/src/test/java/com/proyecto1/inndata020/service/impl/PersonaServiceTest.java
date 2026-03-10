package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.entity.PersonaEntity;
import com.proyecto1.inndata020.model.PersonaDto;
import com.proyecto1.inndata020.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @InjectMocks
    PersonaService personaService;

    @Mock
    PersonaRepository personaRepository;

    @Test
    void readAll_transformaEntityADto(){
        DepartamentoEntity dept = new DepartamentoEntity();
        dept.setId(1);
        dept.setM2(10);
        dept.setPrecio(1000.0);
        dept.setActivo(true);

        PersonaEntity p1 = new PersonaEntity();
        p1.setId(1);
        p1.setNombre("Ana");
        p1.setEdad(25);
        p1.setIdDepartamento(dept);

        PersonaEntity p2 = new PersonaEntity();
        p2.setId(2);
        p2.setNombre("Luis");
        p2.setEdad(30);
        p2.setIdDepartamento(dept);

        Mockito.when(personaRepository.findAll()).thenReturn(Arrays.asList(p1,p2));
        List<PersonaDto> list = personaService.readAll();
        assertEquals(2, list.size());
        assertEquals("Ana", list.get(0).getNombre());
        assertEquals(25, list.get(0).getEdad());
        assertEquals(1, list.get(0).getIdDepartamento());
    }
}
