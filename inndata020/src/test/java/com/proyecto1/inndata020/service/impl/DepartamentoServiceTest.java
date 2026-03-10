package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.model.DepartamentoDtoRequest;
import com.proyecto1.inndata020.repository.DepartamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DepartamentoServiceTest {

    @InjectMocks
    DepartamentoService departamentoService;

    @Mock
    DepartamentoRepository departamentoRepository;

    DepartamentoEntity activo1;
    DepartamentoEntity inactivo;
    DepartamentoEntity activo2;

    @BeforeEach
    void setup(){
        activo1 = new DepartamentoEntity(1,10,1000.0,true);
        inactivo = new DepartamentoEntity(2,15,1500.0,false);
        activo2 = new DepartamentoEntity(3,20,2000.0,true);
    }

    @Test
    void readAll_debeFiltrarSoloActivos(){
        Mockito.when(departamentoRepository.findAll()).thenReturn(Arrays.asList(activo1,inactivo,activo2));
        List<DepartamentoEntity> resultado = departamentoService.readAll();
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(activo1));
        assertTrue(resultado.contains(activo2));
        assertFalse(resultado.contains(inactivo));
    }

    @Test
    void readById_encontrado(){
        Mockito.when(departamentoRepository.findById(1)).thenReturn(Optional.of(activo1));
        Optional<DepartamentoEntity> opt = departamentoService.readById(1);
        assertTrue(opt.isPresent());
        assertEquals(activo1,opt.get());
    }

    @Test
    void readById_noEncontrado(){
        Mockito.when(departamentoRepository.findById(99)).thenReturn(Optional.empty());
        Optional<DepartamentoEntity> opt = departamentoService.readById(99);
        assertFalse(opt.isPresent());
    }

    @Test
    void create_guardaEntityYRetornaMensaje(){
        DepartamentoDtoRequest dto = Mockito.mock(DepartamentoDtoRequest.class);
        Mockito.when(dto.getM2()).thenReturn(12);
        Mockito.when(departamentoRepository.save(any(DepartamentoEntity.class))).thenAnswer(inv -> inv.getArgument(0));
        String mensaje = departamentoService.create(dto);
        assertEquals("Departamento creado de manera exitosa", mensaje);
        ArgumentCaptor<DepartamentoEntity> captor = ArgumentCaptor.forClass(DepartamentoEntity.class);
        Mockito.verify(departamentoRepository).save(captor.capture());
        assertEquals(12, captor.getValue().getM2());
    }

    @Test
    void updateById_existente_actualizaYRetornaMensaje(){
        Mockito.when(departamentoRepository.findById(1)).thenReturn(Optional.of(activo1));
        DepartamentoEntity nuevo = new DepartamentoEntity(1,5,500.0,true);
        String mensaje = departamentoService.updateById(1,nuevo);
        assertEquals("Departamento actualizado", mensaje);
        ArgumentCaptor<DepartamentoEntity> captor = ArgumentCaptor.forClass(DepartamentoEntity.class);
        Mockito.verify(departamentoRepository).save(captor.capture());
        DepartamentoEntity guardado = captor.getValue();
        assertEquals(5, guardado.getM2());
        assertEquals(500.0, guardado.getPrecio());
    }

    @Test
    void updateById_noExistente_retornaMensajeNoActualizado(){
        Mockito.when(departamentoRepository.findById(99)).thenReturn(Optional.empty());
        String mensaje = departamentoService.updateById(99,activo1);
        assertEquals("Departamento no actualizado porque no ha sido creado", mensaje);
        Mockito.verify(departamentoRepository, Mockito.never()).save(any());
    }

    @Test
    void deleteById_existente_marcaInactivoYRetornaMensaje(){
        Mockito.when(departamentoRepository.findById(3)).thenReturn(Optional.of(activo2));
        String mensaje = departamentoService.deleteById(3);
        assertEquals("Departamento Borrado", mensaje);
        ArgumentCaptor<DepartamentoEntity> captor = ArgumentCaptor.forClass(DepartamentoEntity.class);
        Mockito.verify(departamentoRepository).save(captor.capture());
        assertFalse(captor.getValue().getActivo());
    }

    @Test
    void deleteById_noExistente_retornaNoExiste(){
        Mockito.when(departamentoRepository.findById(42)).thenReturn(Optional.empty());
        String mensaje = departamentoService.deleteById(42);
        assertEquals("No existe tal departamento", mensaje);
        Mockito.verify(departamentoRepository, Mockito.never()).save(any());
    }

    @Test
    void m2AndPrecio_delegaAlRepositorio(){
        List<DepartamentoEntity> expected = Arrays.asList(activo1);
        Mockito.when(departamentoRepository.findByM2LessThanAndPrecioIs(20,1500.0)).thenReturn(expected);
        List<DepartamentoEntity> resultado = departamentoService.m2AndPrecio(20,1500.0);
        assertEquals(expected, resultado);
    }

    @Test
    void m2PrecioQ_delegaAlRepositorio(){
        List<DepartamentoEntity> expected = Arrays.asList(activo2);
        Mockito.when(departamentoRepository.menorQueM2Precio(30,2500.0)).thenReturn(expected);
        List<DepartamentoEntity> resultado = departamentoService.m2PrecioQ(30,2500.0);
        assertEquals(expected, resultado);
    }

    @Test
    void sumar_retornaSumaCorrecta(){
        assertEquals(17.5, departamentoService.sumar(8.5,9.0));
    }
}