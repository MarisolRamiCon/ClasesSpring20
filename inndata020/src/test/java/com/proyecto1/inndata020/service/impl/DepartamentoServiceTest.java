package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.repository.DepartamentoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DepartamentoServiceTest {
    @InjectMocks //Para inyeccion de dependencias
    DepartamentoService departamentoService;
    //Para crear objetos falsos voy a usar la anotacion
    @Mock
    DepartamentoRepository departamentoRepository;
    DepartamentoEntity departamento= new DepartamentoEntity(1,10,1000.0,true);
    DepartamentoEntity departamento3= new DepartamentoEntity(3,20,2000.0,true);

    @Test
    void sumar() {
        double resultado= departamentoService.sumar(8.5,9.0);
        //Vamos a comparar el valor que requerimos contra el valor que nos da el codigo
        assertEquals(17.5,resultado);

    }

    @Test
    void updateById() {
        Mockito.when(departamentoRepository.findById(1)).thenReturn(Optional.of(departamento));
        DepartamentoEntity departamentomodificado = new DepartamentoEntity(1,5,500.0,true);
        DepartamentoEntity departamentoActualizado= new DepartamentoEntity();
        departamentoActualizado.setM2(departamentomodificado.getM2());
        departamentoActualizado.setPrecio(departamentomodificado.getPrecio());
        departamentoRepository.save(departamentoActualizado);
        String mensaje=departamentoService.updateById(1,departamentomodificado);
        assertEquals("Departamento actualizado",mensaje );

    }


    @Test
    void testUpdateByIdElse() {
        Mockito.when(departamentoRepository.findById(2)).thenReturn(Optional.empty());
        assertEquals("Departamento no actualizado porque no ha sido creado",departamentoService.updateById(2,departamento));
    }

    @Test
    void deleteById() {
        Mockito.when(departamentoRepository.findById(3)).thenReturn(Optional.of(departamento3));
        departamento3.setActivo(false);
        departamentoRepository.save(departamento3);
        assertEquals("Departamento Borrado",departamentoService.deleteById(3));
    }

    @BeforeEach
    void setUp() {
        System.out.println("Estoy en el metodo setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Estoy en el metodo tearDown");
    }
}