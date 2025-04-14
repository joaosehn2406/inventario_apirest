package com.jceco.inventario_api.services;

import java.util.List;

import com.jceco.inventario_api.dto.FornecedorDTO;

public interface FornecedorService {

	List<FornecedorDTO> findAll();
    FornecedorDTO findById(Long id);
    FornecedorDTO insert(FornecedorDTO dto);
    FornecedorDTO update(Long id, FornecedorDTO dto);
    FornecedorDTO patch(Long id, FornecedorDTO dto);
    void delete(Long id);
}
