package com.jceco.inventario_api.services.impl;

import com.jceco.inventario_api.dto.UsuarioDTO;
import com.jceco.inventario_api.entities.Usuario;
import com.jceco.inventario_api.repositories.UsuarioRepository;
import com.jceco.inventario_api.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO findById(Long id) {
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return toDTO(entity);
    }

    @Override
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setCargo(dto.getCargoEnum());
        entity = repository.save(entity);
        return toDTO(entity);
    }

    @Override
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        entity.setNome(dto.getNome());
        entity.setCargo(dto.getCargoEnum());
        entity = repository.save(entity);
        return toDTO(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UsuarioDTO toDTO(Usuario entity) {
        return new UsuarioDTO(entity.getId(), entity.getNome(), entity.getCargo());
    }
}
