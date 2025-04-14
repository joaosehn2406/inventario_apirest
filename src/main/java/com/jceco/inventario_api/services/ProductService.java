package com.jceco.inventario_api.services;

import java.util.List;

import com.jceco.inventario_api.dto.ProductDTO;
import com.jceco.inventario_api.entities.Categoria;
import com.jceco.inventario_api.entities.Fornecedor;

public interface ProductService {

	List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO findByCategory(Categoria cat);
    ProductDTO findByFornecedor(Fornecedor f);
    ProductDTO insert(ProductDTO dto);
    ProductDTO update(Long id, ProductDTO dto);
    ProductDTO patch(Long id, ProductDTO dto);
    void delete(Long id);
}
