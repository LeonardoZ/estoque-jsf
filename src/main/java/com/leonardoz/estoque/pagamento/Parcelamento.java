package com.leonardoz.estoque.pagamento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;
import com.leonardoz.estoque.produto.Quantidade;

@Embeddable
public class Parcelamento {

	@Enumerated(EnumType.STRING)
	@Column(name = "periodo", nullable = false)
	private Periodo periodo;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "valor", column = @Column(name = "quantos_pagamentos", nullable = false) ) })
	private Quantidade quantasVezes;

	@Enumerated(EnumType.STRING)
	@Column(length = 7)
	private Juros juros;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "taxaPercentual.valor", column = @Column(name = "taxa_de_juros", nullable = false) ) })
	private Porcentagem taxa;

	@Enumerated(EnumType.STRING)
	@Column(length = 7, name = "juros_vencimento")
	private Juros jurosVencimento;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "taxaPercentual.valor", column = @Column(name = "taxa_juros_vencimento") ) })
	private Porcentagem taxaDeJurosDeVencimento;

	public Parcelamento(Periodo periodo, Quantidade quantasVezes, Juros juros, Porcentagem taxa) {
		super();
		this.periodo = periodo;
		this.quantasVezes = quantasVezes;
		this.juros = juros;
		this.taxa = taxa;
	}

	public List<Parcela> calcularParcelas(Dinheiro valorInicial, Date processamentoDoPagamento) {
		// gera datas de vencimento conforme número de parcelas
		List<LocalDate> diasDeVencimento = gerarDiasDeVencimento(processamentoDoPagamento);

		// calcula valor e cria parcelas
		return gerarParcelas(valorInicial, diasDeVencimento);
	}

	private List<LocalDate> gerarDiasDeVencimento(Date processamentoDoPagamento) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(processamentoDoPagamento);
		int d = calendar.get(Calendar.DAY_OF_MONTH);
		int m = calendar.get(Calendar.MONTH);
		int a = calendar.get(Calendar.YEAR);

		LocalDate instant = LocalDate.of(a, ++m, d);

		List<LocalDate> diasDeVencimento = new LinkedList<>();
		for (int i = 0; i < quantasVezes.getValor().intValue(); i++) {
			LocalDate novoDia = calcularNovaData(instant);
			instant = novoDia;
			diasDeVencimento.add(novoDia);
		}
		return diasDeVencimento;
	}

	private List<Parcela> gerarParcelas(Dinheiro valorInicial, List<LocalDate> diasDeVencimento) {
		int numeroDeParcelas = quantasVezes.getValor().intValue();
		List<Parcela> parcelas = null;

		Dinheiro valorParcela = valorInicial.dividir(numeroDeParcelas);

		switch (juros) {
		case COMPOSTO:
			parcelas = calcularParcelasComJurosComposto(diasDeVencimento, valorParcela);
			break;
		case SEM_JUROS:
			parcelas = calcularParcelasSemJuros(diasDeVencimento, valorParcela);
			break;
		case SIMPLES:
			parcelas = calcularParcelasComJurosSimples(diasDeVencimento, valorParcela);
			break;
		default:
			break;
		}
		return parcelas;
	}

	private List<Parcela> calcularParcelasSemJuros(List<LocalDate> diasDeVencimento, final Dinheiro valorParcela) {
		List<Parcela> parcelas;
		parcelas = diasDeVencimento.stream().map(data -> Parcela.de(data, valorParcela)).collect(Collectors.toList());
		return parcelas;
	}

	private List<Parcela> calcularParcelasComJurosSimples(List<LocalDate> diasDeVencimento,
			final Dinheiro valorParcela) {
		final Dinheiro valorUnicoComJuros = taxa.aplicarAumento(valorParcela);

		return diasDeVencimento.stream().map(data -> Parcela.de(data, valorUnicoComJuros)).collect(Collectors.toList());
	}

	private List<Parcela> calcularParcelasComJurosComposto(List<LocalDate> diasDeVencimento,
			final Dinheiro valorParcela) {
		List<Parcela> parcelas = new LinkedList<>();
		Dinheiro parcelaComJurosCumulativos = new Dinheiro(valorParcela.getMontanteBruto());
		for (LocalDate vencimento : diasDeVencimento) {
			parcelaComJurosCumulativos = taxa.aplicarAumento(parcelaComJurosCumulativos);
			parcelas.add(Parcela.de(vencimento, parcelaComJurosCumulativos));
		}
		return parcelas;
	}

	private LocalDate calcularNovaData(LocalDate data) {
		LocalDate novaData = null;
		switch (periodo) {
		case ANUAL:
			novaData = data.plus(1, ChronoUnit.YEARS);
			break;
		case BIMESTRAl:
			novaData = data.plus(2, ChronoUnit.MONTHS);
			break;
		case MENSAL:
			novaData = data.plus(1, ChronoUnit.MONTHS);
			break;
		case QUINZENAL:
			novaData = data.plus(15, ChronoUnit.DAYS);
			break;
		case SEMANAL:
			novaData = data.plus(1, ChronoUnit.WEEKS);
			break;
		case SEMESTRAL:
			novaData = data.plus(6, ChronoUnit.MONTHS);
			break;
		case TRIMESTRAL:
			novaData = data.plus(3, ChronoUnit.MONTHS);
			break;
		default:
			break;
		}
		return novaData;
	}

	public Juros getJurosVencimento() {
		return jurosVencimento;
	}

	public void setJurosVencimento(Juros jurosVencimento) {
		this.jurosVencimento = jurosVencimento;
	}

	public Porcentagem getTaxaDeJurosDeVencimento() {
		return taxaDeJurosDeVencimento;
	}

	public void setTaxaDeJurosDeVencimento(Porcentagem taxaDeJurosDeVencimento) {
		this.taxaDeJurosDeVencimento = taxaDeJurosDeVencimento;
	}

	public Juros getJuros() {
		return juros;
	}

	public void setJuros(Juros juros) {
		this.juros = juros;
	}

	public Porcentagem getTaxa() {
		return taxa;
	}

	public void setTaxa(Porcentagem taxa) {
		this.taxa = taxa;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Quantidade getQuantasVezes() {
		return quantasVezes;
	}

	public void setQuantasVezes(Quantidade quantasVezes) {
		this.quantasVezes = quantasVezes;
	}

}
