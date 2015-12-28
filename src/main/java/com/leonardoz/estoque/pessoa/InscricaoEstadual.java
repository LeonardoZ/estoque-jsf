package com.leonardoz.estoque.pessoa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.valor.StringValueObject;

@Embeddable
public class InscricaoEstadual implements StringValueObject {

	@NotNull
	@Column(name = "inscricao_estadual", nullable = false, length = 17)
	private String valor;

	public InscricaoEstadual() {

	}

	public InscricaoEstadual(String valor) {
		validarValor(valor);
		setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	@Override
	public boolean analise(String input) {
		return true;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof InscricaoEstadual)) {
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
