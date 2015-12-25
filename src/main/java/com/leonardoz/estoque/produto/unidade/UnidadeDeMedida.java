package com.leonardoz.estoque.produto.unidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.produto.categoria.Categoria;

@Entity
@Table(name = "unidade_de_medida")
public class UnidadeDeMedida extends Entidade {

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
		UnidadeDeMedida castOther = (UnidadeDeMedida) other;
		return new EqualsBuilder().append(descricao, castOther.descricao).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(descricao).toHashCode();
	}

}
