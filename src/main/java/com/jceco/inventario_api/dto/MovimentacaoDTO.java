package com.jceco.inventario_api.dto;

import java.time.LocalDate;

public class MovimentacaoDTO {

    private Long id;
    private String descricao;
    private Integer quantidade;
    private String tipo; // "ENTRADA" ou "SAIDA"
    private LocalDate data;

    private Long produtoId;
    private String produtoDescricao;

    public MovimentacaoDTO() {}

    public MovimentacaoDTO(Long id, String descricao, Integer quantidade, String tipo, LocalDate data,
                           Long produtoId, String produtoDescricao) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.data = data;
        this.produtoId = produtoId;
        this.produtoDescricao = produtoDescricao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public String getProdutoDescricao() { return produtoDescricao; }
    public void setProdutoDescricao(String produtoDescricao) { this.produtoDescricao = produtoDescricao; }
}
