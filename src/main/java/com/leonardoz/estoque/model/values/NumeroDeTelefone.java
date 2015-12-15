package com.leonardoz.estoque.model.values;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class NumeroDeTelefone {

	private static final String PADRAO = "^(\\(11\\) [9][0-9]{4}-[0-9]{4})|(\\(1[2-9]\\) [5-9][0-9]{3}-[0-9]{4})|(\\([2-9][1-9]\\) [1-9][0-9]{3}-[0-9]{4})$";
	private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

	@Column(name = "telefone", nullable = false, length = 17)
	private String valor;

	protected NumeroDeTelefone() {

	}

	public NumeroDeTelefone(String valor) {
		if (valor == null || valor.isEmpty()) {
			throw new IllegalArgumentException("Telefone não pode estar vazio.");
		}
		if (!cepValido(valor)) {
			throw new IllegalArgumentException("Telefone em formato inválido!");
		}
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static boolean cepValido(String valor) {
		return avaliadorDePadrao.matcher(valor).matches();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof NumeroDeTelefone)) {
			return false;
		}
		NumeroDeTelefone castOther = (NumeroDeTelefone) other;
		return new EqualsBuilder().append(valor, castOther.valor).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(valor).toHashCode();
	}
	
	
}
