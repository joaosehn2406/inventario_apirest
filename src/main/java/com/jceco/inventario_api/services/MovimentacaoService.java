package com.jceco.inventario_api.services;

import java.util.List;

import com.jceco.inventario_api.dto.MovimentacaoDTO;

public interface MovimentacaoService {

	List<MovimentacaoDTO> findAll();
    MovimentacaoDTO findById(Long id);
    MovimentacaoDTO findByProductId(Long idProd);
    MovimentacaoDTO insert(MovimentacaoDTO dto);
    MovimentacaoDTO update(Long id, MovimentacaoDTO dto);
    MovimentacaoDTO patch(Long id, MovimentacaoDTO dto);
    void delete(Long id);
	
}
