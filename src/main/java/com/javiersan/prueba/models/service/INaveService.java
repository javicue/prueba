package com.javiersan.prueba.models.service;

import java.util.List;

import com.javiersan.prueba.models.entity.Nave;

public interface INaveService {

    public List<Nave> findAll();

    public Nave save(Nave nave);

    public Nave findById(Long id);

    public void delete(Long id);

    public List<Nave> findByNombreLikeIgnoreCase(String term);
    
}
