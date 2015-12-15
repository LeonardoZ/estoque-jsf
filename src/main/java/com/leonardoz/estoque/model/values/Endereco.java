package com.leonardoz.estoque.model.values;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.model.Cidade;

@Embeddable
public class Endereco {

	@Column(name = "rua", nullable = false, length = 40)
	private String rua;

	@Column(name = "bairro", nullable = false, length = 25)
	private String bairro;

	@Column(name = " numero", nullable = false, length = 8)
	private String numero;

	@Embedded
	private Cep cep;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cep getCep() {
		return cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Endereco)) {
			return false;
		}
		Endereco castOther = (Endereco) other;
		return new EqualsBuilder().append(rua, castOther.rua).append(bairro, castOther.bairro)
				.append(numero, castOther.numero).append(cep, castOther.cep).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(rua).append(bairro).append(numero).append(cep).toHashCode();
	}

}
