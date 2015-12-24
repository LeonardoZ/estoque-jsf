package com.leonardoz.estoque.model.values;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class Cpf implements ValueObject<String> {

	private static final String PADRAO = "(\\d{3}.?\\d{3}.?\\d{3}-?\\d{2})";
	private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

	@Column(name = "cpf", nullable = false, length = 11)
	private String valor;

	public Cpf() {

	}

	public Cpf(String valor) {
		if (valor == null || valor.isEmpty()) {
			throw new IllegalArgumentException("Cpf não pode estar vazio.");
		}
		validarValor(valor);
		limparValor();
	}

	@PrePersist
	public void limparValor() {
		this.valor = valor.replace(".", "").replace("-", "");
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		validarValor(valor);
		this.valor = valor;
		limparValor();
	}

	@Override
	public boolean avaliarValor(String input) {
		return avaliadorDePadrao.matcher(input).matches();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Cpf)) {
			return false;
		}
		Cpf castOther = (Cpf) other;
		return new EqualsBuilder().append(valor, castOther.valor).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(valor).toHashCode();
	}

}
