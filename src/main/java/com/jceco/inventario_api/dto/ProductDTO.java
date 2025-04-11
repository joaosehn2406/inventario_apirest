package com.jceco.inventario_api.dto;

public class ProductDTO {

    private Long id; 
    private String descricao;
    private Integer qtdeEstoque;
    private Double valor;

    private Long fornecedorId;
    private String fornecedorNome;

    private Long categoriaId;
    private String categoriaNome;

    public ProductDTO() {}

    public ProductDTO(Long id, String descricao, Integer qtdeEstoque, Double valor,
                      Long fornecedorId, String fornecedorNome,
                      Long categoriaId, String categoriaNome) {
        this.id = id;
        this.descricao = descricao;
        this.qtdeEstoque = qtdeEstoque;
        this.valor = valor;
        this.fornecedorId = fornecedorId;
        this.fornecedorNome = fornecedorNome;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQtdeEstoque() { return qtdeEstoque; }

    public void setQtdeEstoque(Integer qtdeEstoque) { this.qtdeEstoque = qtdeEstoque; }

    public Double getValor() { return valor; }

    public void setValor(Double valor) { this.valor = valor; }

    public Long getFornecedorId() { return fornecedorId; }

    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }

    public String getFornecedorNome() { return fornecedorNome; }

    public void setFornecedorNome(String fornecedorNome) { this.fornecedorNome = fornecedorNome; }

    public Long getCategoriaId() { return categoriaId; }

    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNome() { return categoriaNome; }

    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }
}
