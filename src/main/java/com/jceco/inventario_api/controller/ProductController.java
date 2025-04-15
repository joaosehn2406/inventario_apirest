package com.jceco.inventario_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.jceco.inventario_api.dto.ProductDTO;
import com.jceco.inventario_api.services.impl.ProductServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name = "Produtos", description = "Endpoints para gerenciar produtos")
public class ProductController {
	
	private final ProductServiceImpl service;

	public ProductController(ProductServiceImpl service) {
		this.service = service;
	}
	
	@Operation(summary = "Listar todos os produtos", description = "Retorna uma lista de todos os produtos disponíveis.")
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
	    List<ProductDTO> p = service.findAll();
	    return ResponseEntity.ok(p);
	}

	@Operation(summary = "Buscar produto por ID", description = "Retorna um produto específico baseado no ID.")
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@Parameter(description = "ID do produto") @PathVariable Long id) {
		ProductDTO p = service.findById(id);
		return ResponseEntity.ok().body(p);		
	}
	
	@Operation(summary = "Buscar produto por categoria", description = "Retorna um produto baseado no ID da categoria.")
	@GetMapping("/categoria/{categoriaId}")
	public ResponseEntity<ProductDTO> findByCategoria(@Parameter(description = "ID da categoria") @PathVariable Long categoriaId) {
	    ProductDTO dto = service.findByCategoryId(categoriaId);
	    return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Buscar produto por fornecedor", description = "Retorna um produto baseado no ID do fornecedor.")
	@GetMapping("/fornecedor/{fornecedorId}")
	public ResponseEntity<ProductDTO> findByFornecedor(@Parameter(description = "ID do fornecedor") @PathVariable Long fornecedorId) {
	    ProductDTO dto = service.findByFornecedorId(fornecedorId);
	    return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Criar um novo produto", description = "Cria um novo produto no sistema.")
	@PostMapping
	public ResponseEntity<ProductDTO> post(@RequestBody ProductDTO p) {
		p = service.insert(p);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(p.getId()).toUri();
		return ResponseEntity.created(uri).body(p);
	}
	
	@Operation(summary = "Atualizar produto", description = "Atualiza um produto existente com novos dados.")
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> put(@RequestBody ProductDTO p, @Parameter(description = "ID do produto") @PathVariable Long id) {
		p = service.update(id, p);
		return ResponseEntity.ok().body(p);
	}
	
	@Operation(summary = "Atualizar parcialmente um produto", description = "Atualiza parcialmente os dados de um produto.")
	@PatchMapping("/{id}")
	public ResponseEntity<ProductDTO> patch(@RequestBody ProductDTO p, @Parameter(description = "ID do produto") @PathVariable Long id) {
		p = service.patch(id, p);
		return ResponseEntity.ok().body(p);
	}
	
	@Operation(summary = "Deletar produto", description = "Deleta um produto do sistema com base no ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Parameter(description = "ID do produto") @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
