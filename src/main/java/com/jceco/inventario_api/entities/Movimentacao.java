package com.jceco.inventario_api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
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


    @OneToMany(mappedBy = "movimentacao")
    private List<MovimentacaoProduto> produtos;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "produto_id", referencedColumnName = "id"),
        @JoinColumn(name = "fornecedor_id", referencedColumnName = "fornecedor_id")
    })
    private Product produto;

    public Movimentacao() {}

    public Movimentacao(String descricao, Integer quantidade, TipoMovimentacao tipo, List<MovimentacaoProduto> produtos) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.produtos = produtos;
        this.data = LocalDate.now();
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public TipoMovimentacao getTipo() { return tipo; }
    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public List<MovimentacaoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<MovimentacaoProduto> produtos) {
        this.produtos = produtos;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public Double getSubtotal() {
        return produtos.stream()
                       .mapToDouble(produto -> produto.getProduto().getValor() * produto.getQuantidade())
                       .sum(); 
    }
}
