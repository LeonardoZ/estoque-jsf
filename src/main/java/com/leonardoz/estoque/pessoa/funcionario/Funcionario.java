package com.leonardoz.estoque.pessoa.funcionario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.leonardoz.estoque.modelo.valor.HorarioDeTrabalho;
import com.leonardoz.estoque.pessoa.PessoaFisica;
import com.leonardoz.estoque.pessoa.cliente.ContratoDeTrabalho;
import com.leonardoz.estoque.pessoa.cliente.Filiacao;
import com.leonardoz.estoque.pessoa.cliente.InformacaoEleitoral;
import com.leonardoz.estoque.pessoa.cliente.InformacaoTrabalhista;

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

	@Column(name = "cargo", length = 60)
	private String cargo;

	@Embedded
	private InformacaoTrabalhista informacaoTrabalhista;

	@Embedded
	private InformacaoEleitoral informacaoEleitoral;

	@Embedded
	private HorarioDeTrabalho horarioDeTrabalho;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "funcionario")
	private List<ContratoDeTrabalho> contratosDeTrabalho;

	public Funcionario() {
		super();
		iniciarCampos();
	}

	public void iniciarCampos() {
		if (contratosDeTrabalho == null)
			contratosDeTrabalho = new ArrayList<>();

		if (horarioDeTrabalho == null)
			horarioDeTrabalho = new HorarioDeTrabalho();

		if (informacaoEleitoral == null)
			informacaoEleitoral = new InformacaoEleitoral();

		if (informacaoTrabalhista == null)
			informacaoTrabalhista = new InformacaoTrabalhista();

		if (filiacao == null)
			filiacao = new Filiacao();
	}

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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	public List<ContratoDeTrabalho> getContratosDeTrabalho() {
		return contratosDeTrabalho;
	}

	public void setContratosDeTrabalho(List<ContratoDeTrabalho> contratosDeTrabalho) {
		this.contratosDeTrabalho = contratosDeTrabalho;
	}

}
