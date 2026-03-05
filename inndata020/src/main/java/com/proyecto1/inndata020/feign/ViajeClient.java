package com.proyecto1.inndata020.feign;

import jakarta.websocket.server.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//Asociandolo a lo que hicimos anteriormente, este funge como el repositorio
@FeignClient(name = "viajes", url = "https://6721642e98bbb4d93ca84a26.mockapi.io/api/v4")
public interface ViajeClient {
    @GetMapping("/viajes")
    public List<Viaje> readAll();
    @GetMapping("/viajes/{id}")
    public Viaje readById(@PathVariable Integer id);
}
