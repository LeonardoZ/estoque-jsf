package com.leonardoz.estoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "cidade")
public class Cidade extends Entidade {

	@Column(name = "nome", length = 20, nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

	public Cidade() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Cidade)) {
			return false;
		}
		Cidade castOther = (Cidade) other;
		return new EqualsBuilder().append(nome, castOther.nome).append(estado, castOther.estado).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nome).append(estado).toHashCode();
	}

}
