package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Sesion;

public interface ISesionService {
    
    public List<Sesion> findAll();
    public Sesion findById(long id);
    public Sesion save(Sesion sesion);
    public void delete(long id);
    public Sesion findByUsername(String username);
}
