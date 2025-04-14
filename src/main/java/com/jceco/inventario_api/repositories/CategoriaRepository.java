package com.jceco.inventario_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.inventario_api.entities.Categoria;



public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	boolean existsByIdAndProductsIsNotEmpty(Long categoriaId);

}
