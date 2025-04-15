package com.jceco.inventario_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.inventario_api.dto.MovimentacaoDTO;
import com.jceco.inventario_api.services.impl.MovimentacaoServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mov")
@Tag(name = "Movimentações", description = "Endpoints para gerenciar movimentações de produtos")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoServiceImpl service;

    public MovimentacaoController(MovimentacaoServiceImpl service) {
        super();
        this.service = service;
    }

    @Operation(summary = "Listar todas as movimentações", description = "Retorna uma lista com todas as movimentações cadastradas.")
    @GetMapping
    public ResponseEntity<List<MovimentacaoDTO>> findAll() {
        List<MovimentacaoDTO> mov = service.findAll();
        return ResponseEntity.ok(mov);
    }

    @Operation(summary = "Buscar movimentação por ID", description = "Retorna uma movimentação específica com base no ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> findById(@Parameter(description = "ID da movimentação") @PathVariable Long id) {
        MovimentacaoDTO mov = service.findById(id);
        return ResponseEntity.ok().body(mov);
    }

    @Operation(summary = "Buscar movimentações por ID do produto", description = "Este endpoint retorna as movimentações de um produto específico.")
    @GetMapping("/product/{id}")
    public ResponseEntity<List<MovimentacaoDTO>> findByProductId(@Parameter(description = "ID do produto") @PathVariable Long id) {
        List<MovimentacaoDTO> mov = service.findByProductId(id);
        return ResponseEntity.ok(mov);
    }

    @Operation(summary = "Criar uma nova movimentação", description = "Este endpoint cria uma nova movimentação de produto.")
    @PostMapping
    public ResponseEntity<MovimentacaoDTO> insert(@RequestBody MovimentacaoDTO mov) {
        mov = service.insert(mov);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/mov")
                .buildAndExpand(mov.getId()).toUri();
        return ResponseEntity.created(uri).body(mov);
    }

    @Operation(summary = "Atualizar uma movimentação", description = "Atualiza uma movimentação existente com base no ID.")
    @PutMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> update(@RequestBody MovimentacaoDTO mov, @Parameter(description = "ID da movimentação") @PathVariable Long id) {
        mov = service.update(id, mov);
        return ResponseEntity.ok().body(mov);
    }

    @Operation(summary = "Atualizar parcialmente uma movimentação", description = "Atualiza parcialmente os dados de uma movimentação existente.")
    @PatchMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> patch(@RequestBody MovimentacaoDTO mov, @Parameter(description = "ID da movimentação") @PathVariable Long id) {
        mov = service.patch(id, mov);
        return ResponseEntity.ok().body(mov);
    }

    @Operation(summary = "Deletar movimentação", description = "Deleta uma movimentação específica com base no ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID da movimentação") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
