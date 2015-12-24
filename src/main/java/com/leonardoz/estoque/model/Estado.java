package com.leonardoz.estoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "estado")
public class Estado extends Entidade {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", length = 20, nullable = false)
	private String nome;

	@Column(name = "sigla", length = 2, nullable = false)
	private String sigla;

	@ManyToOne
	@JoinColumn(name = "pais_id", nullable = false)
	private Pais pais;

	public Estado() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Estado)) {
			return false;
		}
		Estado castOther = (Estado) other;
		return new EqualsBuilder().append(nome, castOther.nome).append(sigla, castOther.sigla)
				.append(pais, castOther.pais).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nome).append(sigla).append(pais).toHashCode();
	}

}
