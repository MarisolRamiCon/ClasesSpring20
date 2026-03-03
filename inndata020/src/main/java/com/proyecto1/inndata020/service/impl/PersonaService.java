package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.model.PersonaDto;
import com.proyecto1.inndata020.repository.PersonaRepository;
import com.proyecto1.inndata020.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService implements IPersonaService {
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public List<PersonaDto> readAll() {
        return personaRepository.findAll().stream().map(
                persona->{
                    return new PersonaDto(persona.getNombre(),persona.getEdad(),persona.getIdDepartamento().getId());
                }
        ).toList();

    }
}
