package com.jceco.inventario_api.dto;

import java.time.LocalDate;
import java.util.List;

public class MovimentacaoDTO {
    private Long id;
    private String descricao;
    private Integer quantidade;
    private String tipo;
    private LocalDate data;
    private List<MovimentacaoProdutoDTO> produtos; 

    public MovimentacaoDTO(Long id, String descricao, Integer quantidade, String tipo, LocalDate data, List<MovimentacaoProdutoDTO> produtos) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.data = data;
        this.produtos = produtos;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<MovimentacaoProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<MovimentacaoProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}
