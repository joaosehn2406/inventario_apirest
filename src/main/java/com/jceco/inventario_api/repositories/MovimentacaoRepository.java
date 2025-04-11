package com.jceco.inventario_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jceco.inventario_api.entities.Movimentacao;



public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

}
