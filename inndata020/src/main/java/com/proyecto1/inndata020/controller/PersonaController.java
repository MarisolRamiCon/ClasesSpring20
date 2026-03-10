package com.proyecto1.inndata020.controller;
import com.proyecto1.inndata020.model.PersonaDto;
import com.proyecto1.inndata020.service.impl.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonaController {
    @Autowired
    PersonaService personaService;
    @GetMapping("/personas")
    public List<PersonaDto> readAll(){
        return personaService.readAll();
    }
}
