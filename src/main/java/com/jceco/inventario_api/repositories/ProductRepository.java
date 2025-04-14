package com.jceco.inventario_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.inventario_api.entities.Categoria;
import com.jceco.inventario_api.entities.Fornecedor;
import com.jceco.inventario_api.entities.Product;
import com.jceco.inventario_api.entities.pk.ProductPk;



public interface ProductRepository extends JpaRepository<Product, ProductPk>{
	
	List<Product> findByCategoria(Categoria categoria);
    List<Product> findByFornecedor(Fornecedor fornecedor);
}
