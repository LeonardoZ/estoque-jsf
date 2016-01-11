package com.leonardoz.estoque.pessoa.cliente;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class Filiacao {

	@Column(name = "nome_pai", length = 100)
	private String nomeDoPai;

	@Column(name = "nome_mae", length = 100)
	private String nomeDaMae;

	public Filiacao() {

	}

	public String getNomeDoPai() {
		return nomeDoPai;
	}

	public void setNomeDoPai(String nomeDoPai) {
		this.nomeDoPai = nomeDoPai;
	}

	public String getNomeDaMae() {
		return nomeDaMae;
	}

	public void setNomeDaMae(String nomeDaMae) {
		this.nomeDaMae = nomeDaMae;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Filiacao)) {
			return false;
		}
		Filiacao castOther = (Filiacao) other;
		return new EqualsBuilder().append(nomeDoPai, castOther.nomeDoPai).append(nomeDaMae, castOther.nomeDaMae)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nomeDoPai).append(nomeDaMae).toHashCode();
	}

}
