package com.leonardoz.estoque.model.values;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class Cpf {

	private static final String PADRAO = "(\\d{3}.?\\d{3}.?\\d{3}-?\\d{2})";
	private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

	@Column(name = "cpf", nullable = false, length = 11)
	private String valor;

	protected Cpf() {

	}

	public Cpf(String valor) {
		if (valor == null || valor.isEmpty()) {
			throw new IllegalArgumentException("Cpf não pode estar vazio.");
		}
		if (!cpfValido(valor)) {
			throw new IllegalArgumentException("Cpf em formato inválido!");
		}
		this.valor = valor.replace(".", "").replace("-", "");
	}

	public String getValor() {
		return valor;
	}

	public static boolean cpfValido(String valor) {
		return avaliadorDePadrao.matcher(valor).matches();
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
