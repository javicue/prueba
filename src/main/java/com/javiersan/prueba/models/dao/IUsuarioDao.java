package com.javiersan.prueba.models.dao;


import com.javiersan.prueba.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    public Usuario findByUsername(String nombreUsuario);
	
	
}
