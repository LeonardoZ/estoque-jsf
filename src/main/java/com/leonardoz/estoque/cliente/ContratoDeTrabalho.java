package com.leonardoz.estoque.cliente;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.funcionario.Funcionario;
import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Quantidade;

@Entity(name = "contrato_de_trabalho")
public class ContratoDeTrabalho extends Entidade {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Temporal(TemporalType.DATE)
    @Column(name = "admissao")
    private Date admissao;

    @Temporal(TemporalType.DATE)
    @Column(name = "demissao")
    private Date demissao;

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(column = @Column(name = "salario_base", nullable = false, scale = 2, precision = 10) , name = "valor") })
    private Dinheiro salarioBase;

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(column = @Column(name = "numero_filhos", nullable = false) , name = "valor") })
    private Quantidade numeroDeFilhos;

    @Column(name = "tem_transporte")
    private boolean temTransporte;

    @Column(name = "tem_alimentacao")
    private boolean temAlimentacao;

    @Column(name = "cargo", length = 60)
    private String cargo;

    public ContratoDeTrabalho() {

    }

    @Size(max = 60)
    public String getCargo() {
	return cargo;
    }

    public void setCargo(String cargo) {
	this.cargo = cargo;
    }

    public Funcionario getFuncionario() {
	return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
	this.funcionario = funcionario;
    }

    public Date getAdmissao() {
	return admissao;
    }

    public void setAdmissao(Date admissao) {
	this.admissao = admissao;
    }

    public Date getDemissao() {
	return demissao;
    }

    public void setDemissao(Date demissao) {
	this.demissao = demissao;
    }

    public Dinheiro getSalarioBase() {
	return salarioBase;
    }

    public void setSalarioBase(Dinheiro salarioBase) {
	this.salarioBase = salarioBase;
    }

    public Quantidade getNumeroDeFilhos() {
	return numeroDeFilhos;
    }

    public void setNumeroDeFilhos(Quantidade numeroDeFilhos) {
	this.numeroDeFilhos = numeroDeFilhos;
    }

    public boolean isTemTransporte() {
	return temTransporte;
    }

    public void setTemTransporte(boolean temTransporte) {
	this.temTransporte = temTransporte;
    }

    public boolean isTemAlimentacao() {
	return temAlimentacao;
    }

    public void setTemAlimentacao(boolean temAlimentacao) {
	this.temAlimentacao = temAlimentacao;
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof ContratoDeTrabalho)) {
	    return false;
	}
	ContratoDeTrabalho castOther = (ContratoDeTrabalho) other;
	return new EqualsBuilder().append(funcionario, castOther.funcionario).append(admissao, castOther.admissao)
		.append(demissao, castOther.demissao).append(salarioBase, castOther.salarioBase)
		.append(numeroDeFilhos, castOther.numeroDeFilhos).append(temTransporte, castOther.temTransporte)
		.append(temAlimentacao, castOther.temAlimentacao).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(funcionario).append(admissao).append(demissao).append(salarioBase)
		.append(numeroDeFilhos).append(temTransporte).append(temAlimentacao).toHashCode();
    }

}
