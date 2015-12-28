package com.leonardoz.estoque.pessoa;

import org.junit.Assert;
import org.junit.Test;

public class NumeroDeTelefoneTest {

	@Test
	public void testarConstrutorVazio() {
		Assert.assertNotNull(new NumeroDeTelefone());
	}
	
	@Test
	public void testarTelefoneResidencial() {
		String telefone = "(14) 93841-1433";
		Assert.assertNotNull(new NumeroDeTelefone(telefone));
	}
	
	@Test
	public void testarTelefoneCelularNovo() {
		String telefone = "(23) 2342-3423";
		Assert.assertNotNull(new NumeroDeTelefone(telefone));
	}

}
