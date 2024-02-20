package com.javiersan.prueba.models.service;

import java.util.List;

import com.javiersan.prueba.models.entity.Nave;

public interface IUsuarioService {

    public UserDetails loadUserByUsername(String username);
    
}