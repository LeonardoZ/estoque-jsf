package com.leonardoz.estoque.model.values;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class InscricaoEstadual {

	@NotNull
	@Column(name = "inscricao_estadual", nullable = false, length = 17)
	private String valor;

	public InscricaoEstadual() {

	}

	public InscricaoEstadual(String valor) {
		if (valor == null || valor.isEmpty()) {
			throw new IllegalArgumentException("Inscricão Estadual não pode estar vazia.");
		}
		limparValor();
	}

	public String getValor() {
		return valor;
	}

	public void limparValor() {
		this.valor = valor.replace(".", "").replace("-", "").replace("/", "");

		System.out.println(valor.length());
	}

	public void setValor(String valor) {
		this.valor = valor;
		limparValor(); 
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
