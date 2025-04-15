package com.jceco.inventario_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.inventario_api.dto.UsuarioDTO;
import com.jceco.inventario_api.services.impl.UsuarioServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Endpoints para gerenciar usuários")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl service;
	
	public UsuarioController(UsuarioServiceImpl service) {
		this.service = service;
	}

	@Operation(summary = "Listar todos os usuários")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAll() {
		List<UsuarioDTO> lista = service.findAll();
        return ResponseEntity.ok(lista);
	}

	@Operation(summary = "Buscar usuário por ID")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}

	@Operation(summary = "Criar novo usuário")
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO uDTO) {
		uDTO = service.insert(uDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(uDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(uDTO);
	}

	@Operation(summary = "Atualizar completamente um usuário existente")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO uDTO) {
		uDTO = service.update(id, uDTO);
		return ResponseEntity.ok().body(uDTO);
	}

	@Operation(summary = "Atualizar parcialmente um usuário existente")
	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioDTO> patch (@PathVariable Long id, @RequestBody UsuarioDTO uDTO) {
		uDTO = service.patch(id, uDTO);
		return ResponseEntity.ok().body(uDTO);
	}

	@Operation(summary = "Deletar um usuário por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
