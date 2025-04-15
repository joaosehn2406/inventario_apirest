package com.jceco.inventario_api.dto;

import com.jceco.inventario_api.entities.Categoria;
import com.jceco.inventario_api.entities.Fornecedor;

public class ProductDTO {

    private Long id; 
    private String descricao;
    private Integer qtdeEstoque;
    private Double valor;

    private Fornecedor fornecedor;   
    private Categoria categoria;

    public ProductDTO( ) {};

	public ProductDTO(Long id, String descricao, Integer qtdeEstoque, Double valor, Fornecedor fornecedor,
			Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.qtdeEstoque = qtdeEstoque;
		this.valor = valor;
		this.fornecedor = fornecedor;
		this.categoria = categoria;
	}

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

	public Integer getQtdeEstoque() {
		return qtdeEstoque;
	}

	public void setQtdeEstoque(Integer qtdeEstoque) {
		this.qtdeEstoque = qtdeEstoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

    
}
