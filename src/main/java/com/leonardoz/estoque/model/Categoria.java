package com.leonardoz.estoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "categoria")
public class Categoria extends Entidade {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 25)
	@Column(name = "descricao", nullable = false, length = 25)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Categoria)) {
			return false;
		}
		Categoria castOther = (Categoria) other;
		return new EqualsBuilder().append(descricao, castOther.descricao).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(descricao).toHashCode();
	}

}
