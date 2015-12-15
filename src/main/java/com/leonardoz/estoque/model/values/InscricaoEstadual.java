package com.leonardoz.estoque.model.values;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class InscricaoEstadual {

	@Column(name = "inscricao_estadual", nullable = false, length = 17)
	private String valor;

	protected InscricaoEstadual() {

	}

	public InscricaoEstadual(String valor) {
		if (valor == null || valor.isEmpty()) {
			throw new IllegalArgumentException("Inscricão Estadual não pode estar vazia.");
		}
		this.valor = valor.replace(".", "").replace("-", "").replace("/", "");
	}

	public String getValor() {
		return valor;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Cnpj)) {
			return false;
		}
		InscricaoEstadual castOther = (InscricaoEstadual) other;
		return new EqualsBuilder().append(valor, castOther.valor).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(valor).toHashCode();
	}
}
