package com.jceco.inventario_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_movimentacao_produto")
public class MovimentacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movimentacao_id")
    private Movimentacao movimentacao;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "produto_id", referencedColumnName = "id"),
        @JoinColumn(name = "fornecedor_id", referencedColumnName = "fornecedor_id")
    })
    private Product produto;
    
    private Integer quantidade;

    public MovimentacaoProduto() {}

    public MovimentacaoProduto(Movimentacao movimentacao, Product produto, Integer quantidade) {
        this.movimentacao = movimentacao;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
