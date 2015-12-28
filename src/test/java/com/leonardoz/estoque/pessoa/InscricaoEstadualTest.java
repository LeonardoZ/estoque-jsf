package com.leonardoz.estoque.pessoa;

import org.junit.Assert;
import org.junit.Test;

public class InscricaoEstadualTest {

	@Test
	public void testarConstrutorVazio() {
		Assert.assertNotNull(new InscricaoEstadual());
	}

	@Test
	public void testarConstrutorFormatado() {
		Assert.assertNotNull(new InscricaoEstadual("32.233.333/1333-23"));
	}



	@Test(expected = IllegalArgumentException.class)
	public void testarRejeicaoValorVazio() {
		Assert.assertNotNull(new InscricaoEstadual(""));
	}

}
