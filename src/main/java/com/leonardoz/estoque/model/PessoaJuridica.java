package com.leonardoz.estoque.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.model.values.Cnpj;
import com.leonardoz.estoque.model.values.Email;
import com.leonardoz.estoque.model.values.Endereco;
import com.leonardoz.estoque.model.values.InscricaoEstadual;
import com.leonardoz.estoque.model.values.NumeroDeTelefone;

@MappedSuperclass
public class PessoaJuridica extends Entidade {

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "valor", column = @Column(name = "telefone", nullable = false, length = 17) ) })
	private NumeroDeTelefone telefone;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "valor", column = @Column(name = "celular", nullable = false, length = 17) ) })
	private NumeroDeTelefone celular;

	@Embedded
	private Cnpj cnpj;

	@Embedded
	private InscricaoEstadual inscricaoEstadual;

	@Embedded
	private Endereco endereco;

	@Embedded
	private Email email;

	@Column(name = "observacao")
	private String obs;

	public PessoaJuridica() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cnpj getCnpj() {
		return cnpj;
	}

	public void setCnpj(Cnpj cnpj) {
		this.cnpj = cnpj;
	}

	public InscricaoEstadual getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(InscricaoEstadual inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public NumeroDeTelefone getTelefone() {
		return telefone;
	}

	public void setTelefone(NumeroDeTelefone telefone) {
		this.telefone = telefone;
	}

	public NumeroDeTelefone getCelular() {
		return celular;
	}

	public void setCelular(NumeroDeTelefone celular) {
		this.celular = celular;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof PessoaJuridica)) {
			return false;
		}
		PessoaJuridica castOther = (PessoaJuridica) other;
		return new EqualsBuilder().append(nome, castOther.nome).append(cnpj, castOther.cnpj).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nome).append(cnpj).toHashCode();
	}

}
