package com.javiersan.prueba.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.javiersan.prueba.models.entity.Nave;

public interface INaveDao extends CrudRepository<Nave, String> {
    
}
