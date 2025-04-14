package com.jceco.inventario_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jceco.inventario_api.entities.Movimentacao;



public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

	List<Movimentacao> findByProduto_Id_Id(Long id);

}
