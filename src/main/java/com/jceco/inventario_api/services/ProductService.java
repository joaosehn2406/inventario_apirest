package com.jceco.inventario_api.services;

import java.util.List;

import com.jceco.inventario_api.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO findByCategoryId(Long categoriaId);
    ProductDTO findByFornecedorId(Long fornecedorId);
    ProductDTO insert(ProductDTO dto);
    ProductDTO update(Long id, ProductDTO dto);
    ProductDTO patch(Long id, ProductDTO dto);
    void delete(Long id);
}
