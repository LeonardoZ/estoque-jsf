package com.leonardoz.estoque.funcionario;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.leonardoz.estoque.cliente.ContratoDeTrabalho;
import com.leonardoz.estoque.cliente.Filiacao;
import com.leonardoz.estoque.cliente.InformacaoEleitoral;
import com.leonardoz.estoque.cliente.InformacaoTrabalhista;
import com.leonardoz.estoque.modelo.valor.HorarioDeTrabalho;
import com.leonardoz.estoque.pessoa.PessoaFisica;

@Entity
@Table(name = "funcionario")
public class Funcionario extends PessoaFisica {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento")
    private Date nascimento;

    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private Habilitacao habilitacao;

    @Embedded
    private Filiacao filiacao;

    @Embedded
    private InformacaoTrabalhista informacaoTrabalhista;

    @Embedded
    private InformacaoEleitoral informacaoEleitoral;

    @Embedded
    private HorarioDeTrabalho horarioDeTrabalho;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "funcionario")
    private Set<ContratoDeTrabalho> contratosDeTrabalho;

    public Funcionario() {
	iniciarCampos();
    }

    @PostLoad
    public void iniciarCampos() {
	super.iniciarCampos();
	if (contratosDeTrabalho == null)
	    contratosDeTrabalho = new HashSet<>();

	if (horarioDeTrabalho == null)
	    horarioDeTrabalho = new HorarioDeTrabalho();

	if (informacaoEleitoral == null)
	    informacaoEleitoral = new InformacaoEleitoral();

	if (informacaoTrabalhista == null)
	    informacaoTrabalhista = new InformacaoTrabalhista();

	if (filiacao == null)
	    filiacao = new Filiacao();

	if (horarioDeTrabalho == null) {
	    horarioDeTrabalho = new HorarioDeTrabalho();
	} else {
	    horarioDeTrabalho.carregarDiasDeTrabalho();
	}
    }

    void configurarDetalhesPraPersistencia() {
	horarioDeTrabalho.configurarDiasDeTrabalhoEspecificados();
    }

    @NotNull
    public Date getNascimento() {
	return nascimento;
    }

    public void setNascimento(Date nascimento) {
	this.nascimento = nascimento;
    }

    public Habilitacao getHabilitacao() {
	return habilitacao;
    }

    public void setHabilitacao(Habilitacao habilitacao) {
	this.habilitacao = habilitacao;
    }

    public Filiacao getFiliacao() {
	return filiacao;
    }

    public void setFiliacao(Filiacao filiacao) {
	this.filiacao = filiacao;
    }

    public InformacaoTrabalhista getInformacaoTrabalhista() {
	return informacaoTrabalhista;
    }

    public void setInformacaoTrabalhista(InformacaoTrabalhista informacaoTrabalhista) {
	this.informacaoTrabalhista = informacaoTrabalhista;
    }

    public InformacaoEleitoral getInformacaoEleitoral() {
	return informacaoEleitoral;
    }

    public void setInformacaoEleitoral(InformacaoEleitoral informacaoEleitoral) {
	this.informacaoEleitoral = informacaoEleitoral;
    }

    public HorarioDeTrabalho getHorarioDeTrabalho() {
	return horarioDeTrabalho;
    }

    public void setHorarioDeTrabalho(HorarioDeTrabalho horarioDeTrabalho) {
	this.horarioDeTrabalho = horarioDeTrabalho;
    }

    public Set<ContratoDeTrabalho> getContratosDeTrabalho() {
	return contratosDeTrabalho;
    }

    public void setContratosDeTrabalho(Set<ContratoDeTrabalho> contratosDeTrabalho) {
	this.contratosDeTrabalho = contratosDeTrabalho;
    }

    public void adicionarContrato(ContratoDeTrabalho contrato) {
	contrato.setFuncionario(this);
	this.contratosDeTrabalho.add(contrato);
    }

}
