package com.leonardoz.estoque.model.values;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class Cnpj {

	private static final String PADRAO = "\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}";
	private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

	@NotNull
	@Column(name = "cnpj", nullable = false, length = 15)
	private String valor;

	public Cnpj() {

	}

	public Cnpj(String valor) {
		if (valor == null || valor.isEmpty()) {
			throw new IllegalArgumentException("Cnpj não pode estar vazio.");
		}
		if (!cnpjValido(valor)) {
			throw new IllegalArgumentException("Cnpj em formato inválido!");
		}
		limparValor();
	}

	public String getValor() {
		return valor;
	}

	@PrePersist
	public void limparValor() {
		this.valor = valor.replace(".", "").replace("-", "").replace("/", "");
	}

	public void setValor(String valor) {
		this.valor = valor;
		limparValor();
	}

	public static boolean cnpjValido(String valor) {
		return avaliadorDePadrao.matcher(valor).matches();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Cnpj)) {
			return false;
		}
		Cnpj castOther = (Cnpj) other;
		return new EqualsBuilder().append(valor, castOther.valor).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(valor).toHashCode();
	}

}
