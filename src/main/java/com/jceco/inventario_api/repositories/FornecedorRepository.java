package com.jceco.inventario_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.inventario_api.entities.Fornecedor;


public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

}
