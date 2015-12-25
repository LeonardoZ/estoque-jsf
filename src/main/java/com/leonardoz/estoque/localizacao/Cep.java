package com.leonardoz.estoque.localizacao;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class Cep {

	private static final String PADRAO = "\\d{5}[-]\\d{2}";
	private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

	@Column(name = "cep", length = 9)
	private String valor;

	public Cep() {

	}

	public Cep(String valor) {
		if (valor == null || valor.isEmpty()) {
			throw new IllegalArgumentException("Cep não pode estar vazio.");
		}
		if (!cepValido(valor)) {
			throw new IllegalArgumentException("Cep em formato inválido!");
		}
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public static boolean cepValido(String valor) {
		return avaliadorDePadrao.matcher(valor).matches();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Cep)) {
			return false;
		}
		Cep castOther = (Cep) other;
		return new EqualsBuilder().append(valor, castOther.valor).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(valor).toHashCode();
	}

}
