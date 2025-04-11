package com.jceco.inventario_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.inventario_api.entities.Product;
import com.jceco.inventario_api.entities.pk.ProductPk;



public interface ProductRepository extends JpaRepository<Product, ProductPk>{

}
