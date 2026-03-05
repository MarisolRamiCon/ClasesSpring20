package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.feign.Viaje;
import com.proyecto1.inndata020.service.impl.ViajeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v5")
public class ViajeController {
    @Autowired
    ViajeService viajeService;
    @GetMapping("/viajecitos")
    public List<Viaje> readAll(){
        return viajeService.readAll();
    }
    @GetMapping("/viajecitos/{id}")
    public Viaje readById(@PathVariable Integer id){
        return viajeService.readById(id);
    }
}
