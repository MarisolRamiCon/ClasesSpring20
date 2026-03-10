package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.feign.Viaje;
import com.proyecto1.inndata020.feign.ViajeClient;
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
class ViajeServiceTest {

    @InjectMocks
    com.proyecto1.inndata020.service.impl.ViajeService viajeService;

    @Mock
    ViajeClient viajeClient;

    @Test
    void readAll_delegaAlClienteFeign(){
        Viaje v = new Viaje();
        v.setDestino("Paris");
        Mockito.when(viajeClient.readAll()).thenReturn(Arrays.asList(v));
        List<Viaje> resultado = viajeService.readAll();
        assertEquals(1, resultado.size());
        assertEquals("Paris", resultado.get(0).getDestino());
    }

    @Test
    void readById_excepcionDevuelveNull(){
        Mockito.when(viajeClient.readById(5)).thenThrow(new RuntimeException("nope"));
        assertNull(viajeService.readById(5));
    }
}
