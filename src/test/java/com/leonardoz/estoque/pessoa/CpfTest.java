package com.leonardoz.estoque.pessoa;

import org.junit.Assert;
import org.junit.Test;

public class CpfTest {

	@Test
	public void testarConstrutorVazio() {
		Assert.assertNotNull(new Cpf());
	}

	@Test
	public void testarConstrutorComDigitoUnico() {
		Assert.assertNotNull(new Cpf("333.333.333-1"));
	}

	@Test
	public void testarConstrutorComDigitoDuplo() {
		Assert.assertNotNull(new Cpf("333.333.333-22"));
	}

	@Test
	public void testarCpfFormatado() {
		String valor = "123.456.798-10";
		Cpf cpf = new Cpf(valor);
		Assert.assertEquals(valor, cpf.cpf());
	}

	@Test
	public void testarConstrutorComLetra() {
		Assert.assertNotNull(new Cpf("333.333.333-x"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarRejeicaoValorInvalido() {
		Assert.assertNotNull(new Cpf("333jiasdi333-2"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testarRejeicaoValorVazio() {
		Assert.assertNotNull(new Cpf(""));
	}

}
