package com.proyecto1.inndata020.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "edad")
    private Integer edad;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_departamento")
    private DepartamentoEntity idDepartamento;
}
