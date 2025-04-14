package com.jceco.inventario_api.services;

import java.util.List;

import com.jceco.inventario_api.dto.CategoriaDTO;

public interface CategoriaService {

	List<CategoriaDTO> findAll();
	CategoriaDTO findById(Long id);
	CategoriaDTO insert(CategoriaDTO cat);
	CategoriaDTO update(Long id, CategoriaDTO cat);
	CategoriaDTO patch(Long id, CategoriaDTO cat);
	void delete(Long id);
}
