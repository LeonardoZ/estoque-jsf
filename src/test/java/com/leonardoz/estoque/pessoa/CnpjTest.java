package com.leonardoz.estoque.pessoa;

import org.junit.Assert;
import org.junit.Test;

public class CnpjTest {

	@Test
	public void testarConstrutorVazio() {
		Assert.assertNotNull(new Cnpj());
	}

	@Test
	public void testarCnpjFormatado() {
		String valor = "332.233.333/1333-23";
		Cnpj cnpj = new Cnpj(valor);
		Assert.assertEquals(valor, cnpj.cnpj());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testarRejeicaoValorInvalido() {
		Assert.assertNotNull(new Cnpj("333jiasdi333-2"));
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testarRejeicaoValorVazio() {
		Assert.assertNotNull(new Cnpj(""));
	}

}
