package com.jceco.inventario_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.inventario_api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
