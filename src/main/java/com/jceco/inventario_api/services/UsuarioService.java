package com.jceco.inventario_api.services;

import java.util.List;

import com.jceco.inventario_api.dto.UsuarioDTO;

public interface UsuarioService {

	List<UsuarioDTO> findAll();
    UsuarioDTO findById(Long id);
    UsuarioDTO insert(UsuarioDTO dto);
    UsuarioDTO update(Long id, UsuarioDTO dto);
    void delete(Long id);
}
