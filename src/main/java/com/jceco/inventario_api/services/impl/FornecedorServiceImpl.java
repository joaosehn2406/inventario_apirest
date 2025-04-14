package com.jceco.inventario_api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.jceco.inventario_api.dto.FornecedorDTO;
import com.jceco.inventario_api.entities.Fornecedor;
import com.jceco.inventario_api.exceptions.DataBaseException;
import com.jceco.inventario_api.exceptions.ResourceNotFoundException;
import com.jceco.inventario_api.repositories.FornecedorRepository;
import com.jceco.inventario_api.services.FornecedorService;

import jakarta.persistence.EntityNotFoundException;

public class FornecedorServiceImpl implements FornecedorService {

	
	private FornecedorRepository repository;
	
	
	
	public FornecedorServiceImpl(FornecedorRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<FornecedorDTO> findAll() {
		return repository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public FornecedorDTO findById(Long id) {
		Fornecedor f = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		return toDTO(f);
	}

	@Override
	public FornecedorDTO insert(FornecedorDTO dto) {
		Fornecedor f = new Fornecedor();
		f.setId(dto.getId());
		f.setNome(dto.getNome());
		f.setCnpj(dto.getCnpj());
		f.setEmail(dto.getEmail());
		f.setTelefone(dto.getTelefone());
		f = repository.save(f);
		
		return toDTO(f);
	}

	@Override
	public FornecedorDTO update(Long id, FornecedorDTO dto) {
		
		try {
			Fornecedor f = repository.findById(id)
					.orElseThrow(() -> new RuntimeException("Problena no UPDATE fornecedor"));
			
			f.setEmail(dto.getEmail());
			f.setCnpj(dto.getCnpj());
			f.setNome(dto.getNome());
			f.setTelefone(dto.getTelefone());
			
			return toDTO(repository.save(f));
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	@Override
	public FornecedorDTO patch(Long id, FornecedorDTO dto) {
		Fornecedor f = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Problena no UPDATE fornecedor"));
		
		if (dto.getCnpj() != null) {
			f.setCnpj(dto.getCnpj());
		}
		
		if (dto.getEmail() != null) {
			f.setEmail(dto.getEmail());
		}
		
		if (dto.getNome() != null) {
			f.setNome(dto.getNome());
		}
		
		if (dto.getTelefone() != null) {
			f.setTelefone(dto.getTelefone());
		}
		
		return toDTO(repository.save(f));
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}

		    repository.deleteById(id);
	}
	
	private FornecedorDTO toDTO(Fornecedor f) {
		return new FornecedorDTO(f.getId(), f.getNome(), f.getEmail(), f.getTelefone(), f.getCnpj());
	}
	
}
