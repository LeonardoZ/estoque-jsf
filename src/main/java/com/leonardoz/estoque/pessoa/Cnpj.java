package com.leonardoz.estoque.pessoa;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.valor.StringValueObject;

@Embeddable
public class Cnpj implements StringValueObject {

	private static final String PADRAO = "\\d{3}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}";
	private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

	@NotNull
	@Column(name = "cnpj", nullable = false, length = 15)
	private String valor;

	public Cnpj() {

	}

	public Cnpj(String valor) {
		setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	public String cnpj(){
		return new StringBuilder()
				.append(valor.substring(0,3)).append(".")
				.append(valor.substring(3,6)).append(".")
				.append(valor.substring(6,9)).append("/")
				.append(valor.substring(9,13)).append("-")
				.append(valor.substring(13))
				.toString();
	}
	
	public void setValor(String valor) {
		validarValor(valor);
		this.valor = valor.replace(".", "").replace("-", "").replace("/", "");
	}

	public boolean analise(String valor) {
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
