package com.jceco.inventario_api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.jceco.inventario_api.entities.enums.TipoMovimentacao;

@Entity
@Table(name = "tb_movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Integer quantidade;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo; 

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "produto_id", referencedColumnName = "id"),
        @JoinColumn(name = "fornecedor_id", referencedColumnName = "fornecedor_id")
    })
    private Product produto;


    public Movimentacao() {}

    public Movimentacao(String descricao, Integer quantidade, TipoMovimentacao tipo, Product produto) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.produto = produto;
        this.data = LocalDate.now();
    }

    public Long getId() { return id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQuantidade() { return quantidade; }

    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public TipoMovimentacao getTipo() { return tipo; }

    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }

    public LocalDate getData() { return data; }

    public Product getProduto() { return produto; }

    public void setProduto(Product produto) { this.produto = produto; }

    public Double getSubtotal() {
        return produto.getValor() * quantidade;
    }
}
