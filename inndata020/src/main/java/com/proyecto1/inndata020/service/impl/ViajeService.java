package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.feign.Viaje;
import com.proyecto1.inndata020.feign.ViajeClient;
import com.proyecto1.inndata020.service.IViajeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ViajeService implements IViajeService {

    @Autowired
    ViajeClient viajeClient;
    @Override
    public List<Viaje> readAll() {
        log.info("Estamos en el metodo readAll()");
        return viajeClient.readAll();
    }

    @Override
    public Viaje readById(Integer id) {
        log.info("Estamos en readById");
        try{
            return viajeClient.readById(id);
        } catch (Exception e) {
            log.error("No se encuentra el viaje");
            return null;
        }

    }

}
