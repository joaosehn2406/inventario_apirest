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

import com.jceco.inventario_api.dto.CategoriaDTO;
import com.jceco.inventario_api.services.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaServiceImpl service;

	public CategoriaController(CategoriaServiceImpl service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<CategoriaDTO> cat = service.findAll();
		return ResponseEntity.ok(cat);
		
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
		CategoriaDTO entity = service.findById(id);
		return ResponseEntity.ok().body(entity);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> post(@RequestBody CategoriaDTO cDTO) {
		cDTO = service.insert(cDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cDTO);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> put (@RequestBody CategoriaDTO cDTO, @PathVariable Long id) {
		CategoriaDTO entity = service.update(id, cDTO);
		return ResponseEntity.ok().body(entity);
		
		
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<CategoriaDTO> patch (@RequestBody CategoriaDTO cDTO, @PathVariable Long id) {
		CategoriaDTO entity = service.patch(id, cDTO);
		return ResponseEntity.ok().body(entity);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
