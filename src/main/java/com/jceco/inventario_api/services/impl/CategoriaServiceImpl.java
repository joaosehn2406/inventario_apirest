package com.jceco.inventario_api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.jceco.inventario_api.dto.CategoriaDTO;
import com.jceco.inventario_api.entities.Categoria;
import com.jceco.inventario_api.repositories.CategoriaRepository;
import com.jceco.inventario_api.services.CategoriaService;

import jakarta.persistence.EntityNotFoundException;


public class CategoriaServiceImpl implements CategoriaService{
	
	
	private final CategoriaRepository repository;
	
	
	public CategoriaServiceImpl(CategoriaRepository repository) {
		super();
		this.repository = repository;
	}


	private CategoriaDTO toDTO(Categoria cat) {
		return new CategoriaDTO(cat.getId(), cat.getNome());
	}
	
	
	@Override
	public List<CategoriaDTO> findAll() {
		return repository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	@Override
	public CategoriaDTO findById(Long id) {
		Categoria entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Deu problema no ID"));
		
		return toDTO(entity);
	}
	
	@Override
	public CategoriaDTO insert(CategoriaDTO cat) {
		Categoria entity = new Categoria();
		entity.setId(cat.getId());
		entity.setNome(cat.getNome());
		entity = repository.save(entity);
		return toDTO(entity);
	}
	
	@Override
	public CategoriaDTO update(Long id, CategoriaDTO cat) {
		Categoria entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Problema no update da categoria"));
		
		entity.setId(cat.getId());
		entity.setNome(cat.getNome());
		repository.save(entity);
		return toDTO(entity);
	}
	@Override
	public CategoriaDTO patch(Long id, CategoriaDTO cat) {
		Categoria entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Problema no patch da categoria"));
		
		if (cat.getNome() != null) {
			entity.setNome(cat.getNome());
		}
		
		return toDTO(repository.save(entity));
	}
	
	
	@Override
	public void delete(Long id) {
	    if (!repository.existsById(id)) {
	        throw new EntityNotFoundException("Categoria não encontrada");
	    }

	    if (repository.existsByIdAndProductsIsNotEmpty(id)) {
	        throw new IllegalStateException("Não é possível excluir uma categoria que possui produtos associados");
	    }

	    repository.deleteById(id);
	}


	
}
