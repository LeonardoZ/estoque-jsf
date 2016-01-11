package com.leonardoz.estoque.pagamento;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;
import com.leonardoz.estoque.produto.Quantidade;

public class ParcelaTest {

	Pagamento gerarPagamentoBase(Juros juros, Porcentagem taxa) {
		Pagamento pagamento = new PagamentoFactory().parcelamento().parcelarEm(Periodo.MENSAL, new Quantidade(5))
				.tendoNoVencimentoJurosAoDia(juros, taxa).parcelar().gerarPagamento();
		return pagamento;
	}

	@Test
	public void testeValorDaParcelaNormal() {
		Date pagamento = new Calendar.Builder().setDate(2016, 1, 7).build().getTime();
		Date vencimento = new Calendar.Builder().setDate(2016, 1, 11).build().getTime();
		Dinheiro valor = new Dinheiro(10);
		Porcentagem desconto = Porcentagem.de(5);

		Parcela parcela = new Parcela(vencimento, valor, desconto);
		parcela.setPagamento(gerarPagamentoBase(Juros.SIMPLES, Porcentagem.de(5)));
		parcela.setPagoEm(pagamento);
		parcela.valorASerPago();

		assertEquals(new Dinheiro(9.5), parcela.valorASerPago());
	}

	@Test
	public void testeValorDaParcelaAtrasadoJurosSimples() {
		Date pagamento = new Calendar.Builder().setDate(2016, 1, 21).build().getTime();
		Date vencimento = new Calendar.Builder().setDate(2016, 1, 11).build().getTime();
		Dinheiro valor = new Dinheiro(10);
		Porcentagem desconto = Porcentagem.de(5);

		Parcela parcela = new Parcela(vencimento, valor, desconto);
		parcela.setPagamento(gerarPagamentoBase(Juros.SIMPLES, Porcentagem.de(5)));
		parcela.setPagoEm(pagamento);
		;
		parcela.valorASerPago();

		assertEquals(new Dinheiro(14.25), parcela.valorASerPago());
	}

	@Test
	public void testeValorDaParcelaAtrasadoJurosComposto() {
		Date pagamento = new Calendar.Builder().setDate(2016, 1, 21).build().getTime();
		Date vencimento = new Calendar.Builder().setDate(2016, 1, 11).build().getTime();
		Dinheiro valor = new Dinheiro(10);

		Parcela parcela = new Parcela(vencimento, valor, Porcentagem.NENHUM);
		parcela.setPagamento(gerarPagamentoBase(Juros.COMPOSTO, Porcentagem.de(5)));
		parcela.setPagoEm(pagamento);
		parcela.valorASerPago();

		assertEquals(new Dinheiro(16.29).arredondado(), parcela.valorASerPago().arredondado());
	}
}
