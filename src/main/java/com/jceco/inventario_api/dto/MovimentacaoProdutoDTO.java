package com.jceco.inventario_api.dto;



public class MovimentacaoProdutoDTO {

    private Long produtoId;
    private Integer quantidade;
    private Long fornecedorId;  
    private String produtoDescricao;

    public MovimentacaoProdutoDTO(Long produtoId, Integer quantidade, String produtoDescricao, Long fornecedorId) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.produtoDescricao = produtoDescricao;
        this.fornecedorId = fornecedorId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }
}
