package com.jceco.inventario_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.inventario_api.dto.FornecedorDTO;
import com.jceco.inventario_api.services.impl.FornecedorServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/fornecedores")
@Tag(name = "Fornecedores", description = "Endpoints para gerenciar fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorServiceImpl repository;

	public FornecedorController(FornecedorServiceImpl repository) {
		super();
		this.repository = repository;
	}

	@Operation(summary = "Listar todos os fornecedores", description = "Retorna uma lista de todos os fornecedores cadastrados.")
	@GetMapping
	public ResponseEntity<List<FornecedorDTO>> findAll() {
		List<FornecedorDTO> f = repository.findAll();
		return ResponseEntity.ok(f);
	}

	@Operation(summary = "Buscar fornecedor por ID", description = "Retorna um fornecedor específico baseado no ID fornecido.")
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDTO> findById(@Parameter(description = "ID do fornecedor") @PathVariable Long id) {
		FornecedorDTO f = repository.findById(id);
		return ResponseEntity.ok().body(f);
	}

	@Operation(summary = "Criar um novo fornecedor", description = "Cria um novo fornecedor no sistema.")
	@PostMapping
	public ResponseEntity<FornecedorDTO> post(@RequestBody FornecedorDTO f) {
		f = repository.insert(f);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(f.getId()).toUri();
		return ResponseEntity.created(uri).body(f);
	}

	@Operation(summary = "Atualizar fornecedor", description = "Atualiza um fornecedor existente com base no ID.")
	@PutMapping("/{id}")
	public ResponseEntity<FornecedorDTO> put(@PathVariable Long id, @RequestBody FornecedorDTO fDTO) {
		FornecedorDTO fornecedor = repository.update(id, fDTO);
		return ResponseEntity.ok().body(fornecedor);
	}

	@Operation(summary = "Atualizar parcialmente fornecedor", description = "Atualiza parcialmente os dados de um fornecedor existente.")
	@PatchMapping("/{id}")
	public ResponseEntity<FornecedorDTO> patch(@PathVariable Long id, @RequestBody FornecedorDTO fDTO) {
		FornecedorDTO fornecedor = repository.patch(id, fDTO);
		return ResponseEntity.ok().body(fornecedor);
	}

	@Operation(summary = "Deletar fornecedor", description = "Deleta um fornecedor específico baseado no ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Parameter(description = "ID do fornecedor") @PathVariable Long id) {
		repository.delete(id);
		return ResponseEntity.noContent().build();
	}
}
