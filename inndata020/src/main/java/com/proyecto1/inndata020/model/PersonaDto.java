package com.proyecto1.inndata020.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PersonaDto {
    private String nombre;
    private Integer edad;
    private Integer idDepartamento;
}
