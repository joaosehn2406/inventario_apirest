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

import com.jceco.inventario_api.dto.UsuarioDTO;
import com.jceco.inventario_api.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/users")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl service;
	
	
	public UsuarioController(UsuarioServiceImpl service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAll() {
		List<UsuarioDTO> lista = service.findAll();
        return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO uDTO) {
		uDTO = service.insert(uDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(uDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(uDTO);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO uDTO) {
		uDTO = service.update(id, uDTO);
		return ResponseEntity.ok().body(uDTO);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioDTO> patch (@PathVariable Long id, @RequestBody UsuarioDTO uDTO) {
		uDTO = service.patch(id, uDTO);
		return ResponseEntity.ok().body(uDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
