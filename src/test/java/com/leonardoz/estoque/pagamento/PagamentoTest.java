package com.leonardoz.estoque.pagamento;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;
import com.leonardoz.estoque.produto.Quantidade;

public class PagamentoTest {

	@Test
	public void pagamentoDeveTer5ParcelasDe300Reais() {
		Pagamento pagamento = new PagamentoFactory().forma(FormaPagamento.DINHEIRO)
				.valorPagamento(new Dinheiro(1500.00))
				.parcelamento()
				.parcelarEm(Periodo.MENSAL, new Quantidade(5))
				.parcelar()
				.gerarPagamento();
		List<Parcela> parcelas = pagamento.getParcelas();
		long numeroDeParcelasCom300Reais = parcelas.stream().filter(p -> p.getValor().equals(new Dinheiro(300)))
				.count();
		assertEquals(5, parcelas.size());
		assertEquals(5, numeroDeParcelasCom300Reais);
	}

	@Test
	public void pagamentoDeveTer24ParcelasDe2400_56Reais() {
		Pagamento pagamento = new PagamentoFactory().forma(FormaPagamento.DINHEIRO)
				.parcelamento()
				.parcelarEm(Periodo.MENSAL, new Quantidade(24))
				.parcelar().valorPagamento(new Dinheiro(57_613.44)).gerarPagamento();
		List<Parcela> parcelas = pagamento.getParcelas();
		long parcelaComValor2400_56 = parcelas.stream().filter(p -> p.getValor().equals(new Dinheiro(2400.56))).count();
		assertEquals(24, parcelas.size());
		assertEquals(24, parcelaComValor2400_56);
	}

	@Test
	public void descontoDeveDiminuirValorASerPago() {
		Pagamento pagamento = new PagamentoFactory().forma(FormaPagamento.DINHEIRO)
				.parcelamento()
				.parcelarEm(Periodo.QUINZENAL, new Quantidade(10))
				.parcelar()
				.valorPagamento(new Dinheiro(10_200))
				.descontoDoValorPagamento(Porcentagem.de(5)).gerarPagamento();

		assertEquals(new Dinheiro(9_690.00), pagamento.valorTotalParaPagar());
	}

	@Test
	public void descontoEEntradaDevemDiminuirValorASerPago() {
		Pagamento pagamento = new PagamentoFactory()
				.forma(FormaPagamento.DINHEIRO)
				.parcelamento()
				.parcelarEm(Periodo.QUINZENAL, new Quantidade(10))
				.parcelar()
				.valorPagamento(new Dinheiro(10_200))
				.descontoDoValorPagamento(Porcentagem.de(5))
				.valorDeEntrada(new Dinheiro(200.55))
				.gerarPagamento();

		assertEquals(new Dinheiro(9_489.45), pagamento.valorTotalParaPagar());
	}

	@Test
	public void parcelasDevemTeroMesmoValorComJuros() {
		Pagamento pagamento = new PagamentoFactory().forma(FormaPagamento.DINHEIRO)
				.parcelamento()
				.parcelarEm(Periodo.QUINZENAL, new Quantidade(10))
				.comJuros(Juros.SIMPLES, Porcentagem.de(10))
				.parcelar()
				.valorPagamento(new Dinheiro(2000))
				.gerarPagamento();

		long parcelasDeValor220 = pagamento.getParcelas().stream().filter(p -> p.getValor().equals(new Dinheiro(220)))
				.count();

		assertEquals(10, parcelasDeValor220);
	}

	@Test
	public void parcelasDevemTerValorCumulativo() {
		Pagamento pagamento = new PagamentoFactory().forma(FormaPagamento.DINHEIRO)
				.parcelamento()
				.parcelarEm(Periodo.QUINZENAL, new Quantidade(10))
				.comJuros(Juros.COMPOSTO, Porcentagem.de(10))
				.parcelar()
				.valorPagamento(new Dinheiro(2000))
				.gerarPagamento();

		List<Parcela> collect = pagamento.getParcelas();
		int parcelas = collect.size();
		Parcela ultimaParcela = collect.get(--parcelas);
		assertEquals(10, collect.size());
		assertEquals(new Dinheiro(518.75).arredondado(), ultimaParcela.getValor().arredondado());
	}

	@Test
	public void parcelasComDesconto() {
		Pagamento pagamento = new PagamentoFactory()
				.forma(FormaPagamento.DINHEIRO)
				.parcelamento()
				.parcelarEm(Periodo.QUINZENAL, new Quantidade(10))
				.comJuros(Juros.SIMPLES, Porcentagem.de(10))
				.parcelar()
				.valorPagamento(new Dinheiro(2000))
				.gerarPagamento();

		long parcelasDeValor220 = pagamento.getParcelas().stream().filter(p -> p.getValor().equals(new Dinheiro(220)))
				.count();

		assertEquals(10, parcelasDeValor220);
	}

}
