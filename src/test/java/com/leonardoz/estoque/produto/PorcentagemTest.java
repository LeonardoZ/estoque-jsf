package com.leonardoz.estoque.produto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PorcentagemTest {

	@Test
	public void testaBuilderPorcentagem() {
		// 100%
		QuantidadeFracionada quantidade = new QuantidadeFracionada(100);
		Porcentagem comPorcentagemDe = Porcentagem.comPorcentagemDe(quantidade);

		// compara com centisimal
		assertEquals(new QuantidadeFracionada(1).getValor(), comPorcentagemDe.getTaxaPercentual().getValor());
	}

	@Test
	public void testaBuilderPercentual() {
		// 100%
		QuantidadeFracionada quantidade = new QuantidadeFracionada(0.8);
		Porcentagem comPorcentagemDe = Porcentagem.comTaxa(quantidade);

		// compara com centisimal
		assertEquals(new QuantidadeFracionada(0.8).getValor(), comPorcentagemDe.getTaxaPercentual().getValor());
	}
	
	
	@Test
	public void testeCalculaOPercentual() {
		// 100%
		QuantidadeFracionada quantidade = new QuantidadeFracionada(10);
		Porcentagem valor = Porcentagem.comPorcentagemDe(quantidade);
		Dinheiro dinheiro = Dinheiro.deValorFormatado("R$ 180,00");
		Dinheiro resultado = valor.calcularPercentualDe(dinheiro);
		
		// compara com centisimal
		assertEquals(Dinheiro.deValorFormatado("R$ 18,00"), resultado);
	}
	
	@Test
	public void testeCalculaONovoValorDiminuido() {
		// 100%
		QuantidadeFracionada quantidade = new QuantidadeFracionada(10);
		Porcentagem valor = Porcentagem.comPorcentagemDe(quantidade);
		Dinheiro dinheiro = Dinheiro.deValorFormatado("R$ 180,00");
		Dinheiro resultado = valor.aplicarDiminuicao(dinheiro);
		
		// compara com centisimal
		assertEquals(Dinheiro.deValorFormatado("R$ 162,00"), resultado);
	}
	
	@Test
	public void testeCalculaONovoValorAumentado() {
		// 100%
		QuantidadeFracionada quantidade = new QuantidadeFracionada(10);
		Porcentagem valor = Porcentagem.comPorcentagemDe(quantidade);
		Dinheiro dinheiro = Dinheiro.deValorFormatado("R$ 180,00");
		Dinheiro resultado = valor.aplicarAumento(dinheiro);
		
		// compara com centisimal
		assertEquals(Dinheiro.deValorFormatado("R$ 198,00"), resultado);
	}

}
