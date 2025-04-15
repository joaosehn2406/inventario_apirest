package com.jceco.inventario_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.inventario_api.dto.CategoriaDTO;
import com.jceco.inventario_api.services.impl.CategoriaServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Endpoints para gerenciar categorias de produtos")
public class CategoriaController {
	
	@Autowired
	private CategoriaServiceImpl service;

	public CategoriaController(CategoriaServiceImpl service) {
		super();
		this.service = service;
	}
	
	@Operation(summary = "Listar todas as categorias", description = "Retorna uma lista de todas as categorias cadastradas.")
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<CategoriaDTO> cat = service.findAll();
		return ResponseEntity.ok(cat);
	}
	
	@Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica com base no ID fornecido.")
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> findById(@Parameter(description = "ID da categoria") @PathVariable Long id) {
		CategoriaDTO entity = service.findById(id);
		return ResponseEntity.ok().body(entity);
	}
	
	@Operation(summary = "Criar uma nova categoria", description = "Cria uma nova categoria no sistema.")
	@PostMapping
	public ResponseEntity<CategoriaDTO> post(@RequestBody CategoriaDTO cDTO) {
		cDTO = service.insert(cDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(cDTO);
	}
	
	@Operation(summary = "Atualizar categoria", description = "Atualiza uma categoria existente com base no ID fornecido.")
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> put(@RequestBody CategoriaDTO cDTO, @Parameter(description = "ID da categoria") @PathVariable Long id) {
		CategoriaDTO entity = service.update(id, cDTO);
		return ResponseEntity.ok().body(entity);
	}
	
	@Operation(summary = "Atualizar parcialmente categoria", description = "Atualiza parcialmente os dados de uma categoria existente.")
	@PatchMapping("/{id}")
	public ResponseEntity<CategoriaDTO> patch(@RequestBody CategoriaDTO cDTO, @Parameter(description = "ID da categoria") @PathVariable Long id) {
		CategoriaDTO entity = service.patch(id, cDTO);
		return ResponseEntity.ok().body(entity);
	}
	
	@Operation(summary = "Deletar categoria", description = "Deleta uma categoria específica com base no ID fornecido.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Parameter(description = "ID da categoria") @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
