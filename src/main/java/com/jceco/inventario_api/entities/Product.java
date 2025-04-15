package com.jceco.inventario_api.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jceco.inventario_api.entities.pk.ProductPk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Product {
	
	@EmbeddedId
	private ProductPk id = new ProductPk();
	
	private String descricao;
	private Integer qtdeEstoque;
	private Double valor;
	
	@ManyToOne
	@MapsId("fornecedorId")
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@OneToMany(mappedBy = "produto")
	private List<Movimentacao> movimentacoes = new ArrayList<>();
	
	public Product() {}

	public Product(ProductPk id, String descricao, Integer qtdeEstoque, Double valor, Fornecedor fornecedor,
			Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.qtdeEstoque = qtdeEstoque;
		this.valor = valor;
		this.fornecedor = fornecedor;
		this.categoria = categoria;
	}

	public ProductPk getId() {
		return id;
	}

	public void setId(ProductPk id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getqtdeEstoque() {
		return qtdeEstoque;
	}

	public void setqtdeEstoque(Integer qtdeEstoque) {
		this.qtdeEstoque = qtdeEstoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@JsonIgnore
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@JsonIgnore
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void adicionarMovimentacao(Movimentacao mov) {
	    movimentacoes.add(mov);
	    mov.setProduto(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	};
	
	public Double getSubtotal() {
		return getValor() * getqtdeEstoque();
	}
	
}
