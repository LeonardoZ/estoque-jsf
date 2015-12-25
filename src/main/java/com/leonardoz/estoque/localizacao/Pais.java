package com.leonardoz.estoque.localizacao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.entidade.Entidade;

@Entity
@Table(name = "pais")
public class Pais extends Entidade {

	@Column(name = "nome", length = 20, nullable = false)
	private String nome;

	@Column(name = "sigla", length = 2, nullable = false)
	private String sigla;

	@OneToMany(mappedBy = "pais")
	private Set<Estado> estados;

	public Pais() {

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

	public Set<Estado> getEstados() {
		return estados;
	}

	public void setEstados(Set<Estado> estados) {
		this.estados = estados;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Pais)) {
			return false;
		}
		Pais castOther = (Pais) other;
		return new EqualsBuilder().append(nome, castOther.nome).append(sigla, castOther.sigla).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nome).append(sigla).toHashCode();
	}

}
