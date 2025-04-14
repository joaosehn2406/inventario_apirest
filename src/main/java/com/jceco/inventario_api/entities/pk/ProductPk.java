package com.jceco.inventario_api.entities.pk;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductPk implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long fornecedorId;

	public ProductPk() {}

	public ProductPk(Long id, Long fornecedorId) {
		this.id = id;
		this.fornecedorId = fornecedorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fornecedorId == null) ? 0 : fornecedorId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		ProductPk other = (ProductPk) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (fornecedorId == null) {
			if (other.fornecedorId != null)
				return false;
		} else if (!fornecedorId.equals(other.fornecedorId))
			return false;

		return true;
	}
}
