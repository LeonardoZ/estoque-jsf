package com.leonardoz.estoque.pagamento;

import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;
import com.leonardoz.estoque.produto.Quantidade;

public class PagamentoDTO {

	private FormaPagamento forma;
	private Dinheiro valor;
	private Dinheiro entrada;
	private Porcentagem percentualDeDesconto;
	private Periodo periodo;
	private Quantidade quantasVezes;
	private Juros juros;
	private Porcentagem taxa;
	private Porcentagem taxaVencimento;
	private Juros jurosVencimento;

	public FormaPagamento getForma() {
		return forma;
	}

	public void setForma(FormaPagamento forma) {
		this.forma = forma;
	}

	public Dinheiro getValor() {
		return valor;
	}

	public void setValor(Dinheiro valor) {
		this.valor = valor;
	}

	public Dinheiro getEntrada() {
		return entrada;
	}

	public void setEntrada(Dinheiro entrada) {
		this.entrada = entrada;
	}

	public Porcentagem getPercentualDeDesconto() {
		return percentualDeDesconto;
	}

	public void setPercentualDeDesconto(Porcentagem percentualDeDesconto) {
		this.percentualDeDesconto = percentualDeDesconto;
	}

	public Porcentagem getTaxaVencimento() {
		return taxaVencimento;
	}

	public void setTaxaVencimento(Porcentagem taxaVencimento) {
		this.taxaVencimento = taxaVencimento;
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

	public Juros getJurosVencimento() {
		return jurosVencimento;
	}

	public void setJurosVencimento(Juros jurosVencimento) {
		this.jurosVencimento = jurosVencimento;
	}

}
