package com.leonardoz.estoque.modelo.valor;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class Email {

	private static final String PADRAO = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
	private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

	@Column(name = "email", length = 30)
	private String valor;

	public Email() {

	}

	public Email(String valor) {
		if (valor == null || valor.isEmpty()) {
			throw new IllegalArgumentException("Email não pode estar vazio.");
		}
		if (!emailValido(valor)) {
			throw new IllegalArgumentException("Email em formato inválido!");
		}
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static boolean emailValido(String valor) {
		return avaliadorDePadrao.matcher(valor).matches();
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Email)) {
			return false;
		}
		Email castOther = (Email) other;
		return new EqualsBuilder().append(valor, castOther.valor).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(valor).toHashCode();
	}

}
