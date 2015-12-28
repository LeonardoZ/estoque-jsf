package com.leonardoz.estoque.produto;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

public class QuantidadeTest {

	@Test
	public void testsConstrutor() {
		Assert.assertNotNull(new Quantidade());
	}

	@Test
	public void testeConstrutorComBigInteger() {
		BigInteger valor = new BigInteger("123654987");
		Quantidade quantidade = new Quantidade(valor);
		assertEquals(quantidade.getValor(), valor);
	}

	@Test
	public void testeConstrutorComLong() {
		long valor = 334_543_323_323l;
		BigInteger valorBigInteger = BigInteger.valueOf(valor);
		Quantidade quantidade = new Quantidade(valor);
		assertEquals(quantidade.getValor(), valorBigInteger);
	}

	@Test
	public void testeAumentarValorLong() {
		Quantidade quantidade = new Quantidade(14_455l);
		Quantidade aumentado = quantidade.aumentar(10_000l);
		assertEquals(24_455l, aumentado.getValor().longValue());
	}

	@Test
	public void testeAumentarValorBig() {
		Quantidade quantidade = new Quantidade(14_455l);
		Quantidade aumentado = quantidade.aumentar(BigInteger.valueOf(10_000l));
		assertEquals(24_455l, aumentado.getValor().longValue());
		
	}

	@Test
	public void testeDiminuirValorBig() {
		Quantidade quantidade = new Quantidade(14_455l);
		Quantidade aumentado = quantidade.diminuir(BigInteger.valueOf(10_000l));
		assertEquals(4_455l, aumentado.getValor().longValue());
	}

	@Test
	public void testeDiminuirValorLong() {
		Quantidade quantidade = new Quantidade(14_455l);
		Quantidade aumentado = quantidade.diminuir(10_000l);
		assertEquals(4_455l, aumentado.getValor().longValue());
	}

}
