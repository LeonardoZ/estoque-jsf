package com.leonardoz.estoque.pessoa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.leonardoz.estoque.localizacao.Endereco;
import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.modelo.valor.Email;

@MappedSuperclass
public class PessoaJuridica extends Entidade {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Column(name = "nome_fantasia", nullable = false, length = 100)
    private String nomeFantasia;

    @NotEmpty
    @Column(name = "razao_social", nullable = false, length = 100)
    private String razaoSocial;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "valor", column = @Column(name = "telefone", length = 17) ) })
    private NumeroDeTelefone telefone;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "valor", column = @Column(name = "celular", length = 17) ) })
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
	iniciarCampos();
    }

    public void iniciarCampos() {
	if (inscricaoEstadual == null)
	    inscricaoEstadual = new InscricaoEstadual();

	if (telefone == null)
	    telefone = new NumeroDeTelefone();

	if (celular == null)
	    celular = new NumeroDeTelefone();

	if (cnpj == null)
	    cnpj = new Cnpj();

	if (endereco == null)
	    endereco = new Endereco();

	if (email == null)
	    email = new Email();

    }

    public String getNomeFantasia() {
	return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
	this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
	return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
	this.razaoSocial = razaoSocial;
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
	return new EqualsBuilder().append(nomeFantasia, castOther.nomeFantasia)
		.append(razaoSocial, castOther.razaoSocial).append(cnpj, castOther.cnpj)
		.append(inscricaoEstadual, castOther.inscricaoEstadual).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(nomeFantasia).append(razaoSocial).append(cnpj).append(inscricaoEstadual)
		.toHashCode();
    }

}
