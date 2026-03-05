package com.proyecto1.inndata020.service;

import com.proyecto1.inndata020.feign.Viaje;

import java.util.List;

public interface IViajeService {
    public List<Viaje> readAll();
    public Viaje readById(Integer id);
}
