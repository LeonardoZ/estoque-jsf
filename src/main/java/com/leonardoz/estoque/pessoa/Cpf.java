package com.leonardoz.estoque.pessoa;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.valor.StringValueObject;

/*
 * Não considera se o CPF por é valido por motivos de facilitar o desenvolvimento.
 */
@Embeddable
public class Cpf implements StringValueObject {

	private static final String PADRAO = "(\\d{3}.?\\d{3}.?\\d{3}-?\\w{1,2})";
	private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

	@Column(name = "cpf", nullable = false, length = 11)
	private String valor;

	public Cpf() {
	}

	public Cpf(String valor) {
		validarValor(valor);
		setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	public String cpf() {
		 return new StringBuilder()
		 		.append(valor.substring(0, 3)).append(".")
				.append(valor.substring(3, 6)).append(".")
				.append(valor.substring(6, 9)).append("-")
				.append(valor.substring(9))
				.toString();
	}

	public void setValor(String valor) {
		validarValor(valor);
		this.valor = valor.replace(".", "").replace("-", "");
	}

	@Override
	public boolean analise(String input) {
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
