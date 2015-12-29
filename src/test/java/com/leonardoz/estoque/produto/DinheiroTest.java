package com.leonardoz.estoque.produto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class DinheiroTest {

	@Test
	public void testeDinheiro() {
		assertNotNull(new Dinheiro());
	}

	@Test
	public void testeDinheiroString() {
		assertNotNull(new Dinheiro("1.23"));
	}
	

	@Test
	public void testeDinheiroSemCifraoString() {
		assertNotNull(Dinheiro.deValorFormatado("R$ 1,23"));
	}
	
	@Test
	public void testeDinheiroFormatadoSimplesString() {
		Dinheiro valorFormatado = Dinheiro.deValorFormatado("R$ 1,23");
		String simples = valorFormatado.valorFormatadoSimples();
		assertEquals("1,23", simples);
	}

	@Test
	public void testeDinheiroBigDecimal() {
		assertNotNull(new Dinheiro(BigDecimal.valueOf(1.23)));
	}

	@Test
	public void testeDinheiroDouble() {
		assertNotNull(new Dinheiro(BigDecimal.valueOf(1.23d)));
	}

	@Test
	public void testeDinheiroLong() {
		assertNotNull(new Dinheiro(BigDecimal.valueOf(544)));
	}

	@Test
	public void testeValorFormatado() {
		Dinheiro dinheiro = new Dinheiro(1.25);
		assertEquals("R$ 1,25", dinheiro.valorFormatado());
	}

	@Test
	public void testeGetMontante() {
		Dinheiro dinheiro = new Dinheiro(1.25);
		Dinheiro novoDinheiro = dinheiro.getMontante();
		assertEquals(dinheiro, novoDinheiro);
	}

	@Test
	public void testeSomar() {
		Dinheiro dinheiro = new Dinheiro(10.00);
		Dinheiro valor = new Dinheiro(1.25);
		Dinheiro sub = dinheiro.somar(valor);
		assertEquals(BigDecimal.valueOf(11.25d), sub.getMontanteBruto());
	}

	@Test
	public void testeSubtrair() {
		Dinheiro dinheiro = new Dinheiro(10.00);
		Dinheiro valor = new Dinheiro(1.25);
		Dinheiro sub = dinheiro.subtrair(valor);
		assertEquals(BigDecimal.valueOf(8.75d), sub.getMontanteBruto());
	}

	@Test
	public void testeMultiplicarLong() {
		Dinheiro dinheiro = new Dinheiro(10.00);
		Dinheiro multi = dinheiro.multiplicar(4.5);
		assertEquals(45.00d, multi.getMontanteBruto().doubleValue(), 0.01d);
	}

	@Test
	public void testeMultiplicarDouble() {
		Dinheiro dinheiro = new Dinheiro(10.00);
		Dinheiro multi = dinheiro.multiplicar(4);
		assertEquals(40l, multi.getMontanteBruto().longValue());
	}

	@Test
	public void testeDividirLong() {
		Dinheiro dinheiro = new Dinheiro(10.00);
		Dinheiro dividido = dinheiro.dividir(4);
		assertEquals(new Dinheiro(2.5), dividido);
	}

	@Test
	public void testeDividirDouble() {
		Dinheiro dinheiro = new Dinheiro(10.00);
		Dinheiro dividido = dinheiro.dividir(4.5);
		assertEquals(new Dinheiro(2.22d).valorFormatado(), dividido.valorFormatado());
	}

	@Test
	public void testeIsZero() {
		Dinheiro dinheiro = new Dinheiro(0);
		assertTrue(dinheiro.isZero());
	}

	@Test
	public void testeIsMaior() {
		Dinheiro dinheiro = new Dinheiro(100);
		Dinheiro maior = new Dinheiro(200);
		assertTrue(maior.isMaior(dinheiro));
	}

	@Test
	public void testeIsMenor() {
		Dinheiro dinheiro = new Dinheiro(100);
		Dinheiro maior = new Dinheiro(200);
		assertTrue(dinheiro.isMenor(maior));
	}

	@Test
	public void testeIsMaiorIgual() {
		Dinheiro dinheiro = new Dinheiro(100);
		Dinheiro maior = new Dinheiro(100);
		assertTrue(maior.isMaiorIgual(dinheiro));
	}

	@Test
	public void testeIsMenorIgual() {
		Dinheiro dinheiro = new Dinheiro(100);
		Dinheiro maior = new Dinheiro(100);
		assertTrue(maior.isMenorIgual(dinheiro));
	}

	@Test
	public void testePercentual() {
		Dinheiro dinheiro = new Dinheiro(200);
		Dinheiro percentual = dinheiro.percentual(50);
		assertEquals(new Dinheiro(100.0d), percentual);
	}

	@Test
	public void testeDividirParaInteiroDouble() {
		Dinheiro dinheiro = new Dinheiro(10);
		int dividirParaInteiro = dinheiro.dividirParaInteiro(3.5);
		assertEquals(2, dividirParaInteiro);
	}

	@Test
	public void testeDividirParaInteiroLong() {
		Dinheiro dinheiro = new Dinheiro(10);
		int dividirParaInteiro = dinheiro.dividirParaInteiro(3);
		assertEquals(3, dividirParaInteiro);
	}

	@Test
	public void testeDividirParaInteiroBigDecimal() {
		Dinheiro dinheiro = new Dinheiro(10);
		int dividirParaInteiro = dinheiro.dividirParaInteiro(BigDecimal.valueOf(9));
		assertEquals(1, dividirParaInteiro);
	}

	@Test
	public void testeInverter() {
		Dinheiro dinheiro = new Dinheiro(10);
		Dinheiro invertido = dinheiro.inverter();
		assertEquals(new Dinheiro(-10), invertido);
	}

	@Test
	public void testeMax() {
		Dinheiro dinheiro = new Dinheiro(10);
		Dinheiro param = new Dinheiro(20);
		Dinheiro max = dinheiro.max(param);
		assertEquals(new Dinheiro(new BigDecimal("20.00")), max);
	}

	@Test
	public void testeMin() {
		Dinheiro dinheiro = new Dinheiro(10);
		Dinheiro param = new Dinheiro(20);
		Dinheiro min = dinheiro.min(param);
		assertEquals(new Dinheiro(new BigDecimal("10.00")), min);
	}

}
