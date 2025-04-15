package com.jceco.inventario_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.inventario_api.dto.ProductDTO;
import com.jceco.inventario_api.services.impl.ProductServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@RestController
@RequestMapping("/products")
@Tag(name = "Produtos", description = "Endpoint para gerenciar produtos")
public class ProductController {
	
	private ProductServiceImpl service;

	public ProductController(ProductServiceImpl service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
	    List<ProductDTO> p = service.findAll();
	    return ResponseEntity.ok(p);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO p = service.findById(id);
		return ResponseEntity.ok().body(p);		
	}
	
	@GetMapping("/categoria/{categoriaId}")
	public ResponseEntity<ProductDTO> findByCategoria(@PathVariable Long categoriaId) {
	    ProductDTO dto = service.findByCategoryId(categoriaId);
	    return ResponseEntity.ok(dto);
	}

	@GetMapping("/fornecedor/{fornecedorId}")
	public ResponseEntity<ProductDTO> findByFornecedor(@PathVariable Long fornecedorId) {
	    ProductDTO dto = service.findByFornecedorId(fornecedorId);
	    return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> post(@RequestBody ProductDTO p) {
		p = service.insert(p);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/products")
				.buildAndExpand(p.getId()).toUri();
		
		return ResponseEntity.created(uri).body(p);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> put(@RequestBody ProductDTO p, @PathVariable Long id) {
		p = service.update(id, p);
		return ResponseEntity.ok().body(p);
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ProductDTO> patch(@RequestBody ProductDTO p, @PathVariable Long id) {
		p = service.patch(id, p);
		return ResponseEntity.ok().body(p);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
