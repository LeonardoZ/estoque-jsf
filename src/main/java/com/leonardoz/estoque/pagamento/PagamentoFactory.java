package com.leonardoz.estoque.pagamento;

import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;
import com.leonardoz.estoque.produto.Quantidade;

public class PagamentoFactory {

	private FormaPagamento forma;
	private Dinheiro valor;
	private Dinheiro entrada;
	private Porcentagem percentualDeDesconto;
	private Parcelamento parcelamento;
	
	public PagamentoFactory forma(FormaPagamento forma) {
		this.forma = forma;
		return this;
	}

	public PagamentoFactory valorPagamento(Dinheiro valor) {
		this.valor = valor;
		return this;
	}

	public PagamentoFactory descontoDoValorPagamento(Porcentagem percentualDeDesconto) {
		this.percentualDeDesconto = percentualDeDesconto;
		return this;
	}

	public PagamentoFactory valorDeEntrada(Dinheiro valor) {
		this.entrada = valor;
		return this;
	}

	public ParcelamentoBuilder parcelamento() {
		ParcelamentoBuilder builder = new ParcelamentoBuilder();
		return builder;
	}
	
	public PagamentoFactory pagamentoAVista() {
		ParcelamentoBuilder builder = new ParcelamentoBuilder();
		builder.parcelar();
		return this;
	}
	
	
	private PagamentoFactory configuraParcelamento(Parcelamento p) {
		this.parcelamento = p;
		return this;
	}

	public class ParcelamentoBuilder {

		private Quantidade quantidade;
		private Periodo periodo;
		private Porcentagem taxa;
		private Juros juros;
		private Juros jurosVencimento;
		private Porcentagem taxaVencimento;

		public ParcelamentoBuilder() {
			this.periodo = Periodo.A_VISTA;
			this.quantidade = new Quantidade(1);
			this.juros = Juros.SEM_JUROS;
			this.taxa = Porcentagem.NENHUM;
			this.jurosVencimento = Juros.SEM_JUROS;
			this.taxaVencimento = Porcentagem.NENHUM;
		}

		public ParcelamentoBuilder parcelarEm(Periodo periodo, Quantidade quantasVezes) {
			this.periodo = periodo;
			this.quantidade = quantasVezes;
			return this;
		}

		public ParcelamentoBuilder comJuros(Juros juros, Porcentagem taxa) {
			this.juros = juros;
			this.taxa = taxa;
			return this;
		}

		public ParcelamentoBuilder tendoNoVencimentoJurosAoDia(Juros jurosVencimento, Porcentagem taxaVencimento) {
			this.jurosVencimento = jurosVencimento;
			this.taxaVencimento = taxaVencimento;
			return this;
		}

		public PagamentoFactory parcelar() {
			Parcelamento parcelamento = new Parcelamento(periodo, quantidade, juros, taxa);
			parcelamento.setJurosVencimento(jurosVencimento);
			parcelamento.setTaxaDeJurosDeVencimento(taxaVencimento);
			return configuraParcelamento(parcelamento);
		}
	}

	public Pagamento gerarPagamento() {
		Pagamento pagamento = new Pagamento();
		if (forma != null)
			pagamento.setForma(forma);
		if (valor != null)
			pagamento.setValorPagamento(valor);
		if (entrada != null)
			pagamento.setEntrada(entrada);
		if (percentualDeDesconto != null)
			pagamento.setDescontoValorPagamento(percentualDeDesconto);
		pagamento.setStatus(StatusPagamento.ABERTO);
		pagamento.setParcelamento(parcelamento);
		pagamento.gerarParcelas();
		return pagamento;
	}

}
