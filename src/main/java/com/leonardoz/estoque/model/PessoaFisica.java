package com.leonardoz.estoque.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.model.values.Cpf;
import com.leonardoz.estoque.model.values.Email;
import com.leonardoz.estoque.model.values.Endereco;
import com.leonardoz.estoque.model.values.NumeroDeTelefone;

@MappedSuperclass
public class PessoaFisica extends Entidade {

	private static final long serialVersionUID = 1L;

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

	@Column(name = "rg", nullable = false, length = 20)
	private String rg;

	@Embedded
	private Cpf cpf;
	
	@Embedded
	private Email email;

	@Embedded
	private Endereco endereco;

	public PessoaFisica() {
		iniciarCampos();
	}

	public void iniciarCampos() {
		if (cpf == null)
			cpf = new Cpf();

		if (telefone == null)
			telefone = new NumeroDeTelefone();

		if (celular == null)
			celular = new NumeroDeTelefone();

		if (endereco == null)
			endereco = new Endereco();

		if (email == null)
			email = new Email();

	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public Email getEmail() {
		return email;
	}
	
	public void setEmail(Email email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof PessoaFisica)) {
			return false;
		}
		PessoaFisica castOther = (PessoaFisica) other;
		return new EqualsBuilder().append(nome, castOther.nome).append(telefone, castOther.telefone)
				.append(celular, castOther.celular).append(rg, castOther.rg).append(cpf, castOther.cpf)
				.append(endereco, castOther.endereco).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nome).append(telefone).append(celular).append(rg).append(cpf)
				.append(endereco).toHashCode();
	}

}
