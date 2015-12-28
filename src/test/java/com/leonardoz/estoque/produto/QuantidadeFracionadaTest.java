package com.leonardoz.estoque.produto;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class QuantidadeFracionadaTest {

	@Test
	public void testsConstrutor() {
		Assert.assertNotNull(new QuantidadeFracionada());
	}

	@Test
	public void testeConstrutorComBigDecimal() {
		BigDecimal valor = new BigDecimal("123654987.23");
		QuantidadeFracionada quantidade = new QuantidadeFracionada(valor);
		assertEquals(quantidade.getValor(), valor);
	}

	@Test
	public void testeConstrutorComLong() {
		double valor = 334_543_323_323.45d;
		BigDecimal valorBigDecimal = BigDecimal.valueOf(valor);
		QuantidadeFracionada quantidade = new QuantidadeFracionada(valor);
		assertEquals(quantidade.getValor(), valorBigDecimal);
	}

	@Test
	public void testeAumentarValorLong() {
		QuantidadeFracionada quantidade = new QuantidadeFracionada(14_455.22d);
		QuantidadeFracionada aumentado = quantidade.aumentar(10_000.11d);
		assertEquals(24_455.33d, aumentado.getValor().doubleValue(), 0.1d);
	}

	@Test
	public void testeAumentarValorBig() {
		QuantidadeFracionada quantidade = new QuantidadeFracionada(14_455.22d);
		QuantidadeFracionada aumentado = quantidade.aumentar(BigDecimal.valueOf(10_000.11d));
		assertEquals(24_455.33d, aumentado.getValor().doubleValue(), 0.1d);

	}

	@Test
	public void testeDiminuirValorBig() {
		QuantidadeFracionada quantidade = new QuantidadeFracionada(14_455.22d);
		QuantidadeFracionada aumentado = quantidade.diminuir(BigDecimal.valueOf(10_000.11d));
		assertEquals(4_455.11d, aumentado.getValor().doubleValue(), 0.1d);
	}

	@Test
	public void testeDiminuirValorLong() {
		QuantidadeFracionada quantidade = new QuantidadeFracionada(14_455.22d);
		QuantidadeFracionada aumentado = quantidade.diminuir(10_000.11d);
		assertEquals(4_455.11d, aumentado.getValor().doubleValue(), 0.1d);
	}

}
