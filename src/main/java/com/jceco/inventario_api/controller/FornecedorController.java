package com.jceco.inventario_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jceco.inventario_api.dto.FornecedorDTO;
import com.jceco.inventario_api.services.impl.FornecedorServiceImpl;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorServiceImpl repository;

	public FornecedorController(FornecedorServiceImpl repository) {
		super();
		this.repository = repository;
	}
	
	
	@GetMapping
	public ResponseEntity<List<FornecedorDTO>> findAll() {
		List<FornecedorDTO> f = repository.findAll();
		return ResponseEntity.ok(f);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDTO> findById(@PathVariable Long id) {
		FornecedorDTO f = repository.findById(id);
		return ResponseEntity.ok().body(f);
	}
	
	@PostMapping
	public ResponseEntity<FornecedorDTO> post(@RequestBody FornecedorDTO f) {
		f = repository.insert(f);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(f.getId()).toUri();
		
		return ResponseEntity.created(uri).body(f);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FornecedorDTO> put(@PathVariable Long id, @RequestBody FornecedorDTO fDTO) {
		FornecedorDTO fornecedor = repository.update(id, fDTO);
		return ResponseEntity.ok().body(fornecedor);
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<FornecedorDTO> patch(@PathVariable Long id, @RequestBody FornecedorDTO fDTO) {
		FornecedorDTO fornecedor = repository.patch(id, fDTO);
		return ResponseEntity.ok().body(fornecedor);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		repository.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
