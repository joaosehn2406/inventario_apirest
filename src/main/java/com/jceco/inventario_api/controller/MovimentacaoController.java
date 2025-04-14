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

import com.jceco.inventario_api.dto.MovimentacaoDTO;
import com.jceco.inventario_api.services.impl.MovimentacaoServiceImpl;


@RestController
@RequestMapping("/mov")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoServiceImpl service;
	


	public MovimentacaoController(MovimentacaoServiceImpl service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<MovimentacaoDTO>> findAll() {
		List<MovimentacaoDTO> mov = service.findAll();
		return ResponseEntity.ok(mov);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovimentacaoDTO> findById(@PathVariable Long id) {
		MovimentacaoDTO mov = service.findById(id);
		return ResponseEntity.ok().body(mov);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<List<MovimentacaoDTO>> findByProductId(@PathVariable Long prodId) {
		List<MovimentacaoDTO> mov = service.findByProductId(prodId);
		return ResponseEntity.ok(mov);
	}
	
	@PostMapping
	public ResponseEntity<MovimentacaoDTO> insert (@RequestBody MovimentacaoDTO mov) {
		
		mov = service.insert(mov);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/mov")
				.buildAndExpand(mov.getId()).toUri();
		
		return ResponseEntity.created(uri).body(mov);
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovimentacaoDTO> update(@RequestBody MovimentacaoDTO mov, @PathVariable Long id) {
		mov = service.update(id, mov);
		
		return ResponseEntity.ok().body(mov);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<MovimentacaoDTO> patch(@RequestBody MovimentacaoDTO mov, @PathVariable Long id) {
		mov = service.patch(id, mov);
		
		return ResponseEntity.ok().body(mov);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
