package com.leonardoz.estoque.pessoa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.leonardoz.estoque.localizacao.Endereco;
import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.modelo.valor.Email;

@MappedSuperclass
public class PessoaFisica extends Entidade {

    private static final long serialVersionUID = 1L;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Convert(converter = SexoJpaConverter.class)
    private Sexo sexo;

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

    @NotEmpty
    @Size(max = 100)
    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    @NotEmpty
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

    public Sexo getSexo() {
	return sexo;
    }

    public void setSexo(Sexo sexo) {
	this.sexo = sexo;
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
