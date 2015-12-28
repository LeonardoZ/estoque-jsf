package com.leonardoz.estoque.produto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProdutoTest {

	@Test
	public void testeConstrutor() {

	}
	

	@Test
	public void testeAumentarPrecoCusto() {
		Dinheiro custo = new Dinheiro(10.4);
		Dinheiro aumento = new Dinheiro(9.6);

		Produto produto = new Produto();
		produto.setPrecoDeCusto(custo);
		produto.aumentarPrecoDeCusto(aumento);

		assertEquals(new Dinheiro(20.00d), produto.getPrecoDeCusto());

	}

	@Test
	public void testeDiminuirPrecoCusto() {
		Dinheiro custo = new Dinheiro(20.0);
		Dinheiro valor = new Dinheiro(9.6);

		Produto produto = new Produto();
		produto.setPrecoDeCusto(custo);
		produto.diminuirPrecoDeCusto(valor);

		assertEquals(new Dinheiro(10.4d), produto.getPrecoDeCusto());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testeNaoDiminuirPrecoCusto() {
		Dinheiro custo = new Dinheiro(20.0);
		Dinheiro valor = new Dinheiro(23.4);

		Produto produto = new Produto();
		produto.setPrecoDeCusto(custo);
		produto.diminuirPrecoDeCusto(valor);
	}

	@Test
	public void testeAumentarPrecoVenda() {
		Dinheiro custo = new Dinheiro(10.4);
		Dinheiro venda = new Dinheiro(12);
		Dinheiro aumento = new Dinheiro(1.2);

		Produto produto = new Produto();
		produto.setPrecoDeCusto(custo);
		produto.setPrecoDeVenda(venda);

		produto.aumentarPrecoDeVenda(aumento);

		assertEquals(new Dinheiro(13.2d), produto.getPrecoDeVenda());
	}

	@Test
	public void testeDiminuirPrecoVenda() {
		Dinheiro custo = new Dinheiro(10.4);
		Dinheiro venda = new Dinheiro(12);
		Dinheiro valor = new Dinheiro(1.2);

		Produto produto = new Produto();
		produto.setPrecoDeCusto(custo);
		produto.setPrecoDeVenda(venda);

		produto.diminuirPrecoDeVenda(valor);

		assertEquals(new Dinheiro(10.8d), produto.getPrecoDeVenda());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testeNaoDiminuirPrecoVenda() {
		Dinheiro custo = new Dinheiro(10.4);
		Dinheiro venda = new Dinheiro(12);
		Dinheiro valor = new Dinheiro(13);

		Produto produto = new Produto();
		produto.setPrecoDeCusto(custo);
		produto.setPrecoDeVenda(venda);

		produto.diminuirPrecoDeVenda(valor);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testeNaoPrecoVendaDeveSerMaiorQueDeCusto() {
		Dinheiro custo = new Dinheiro(10.4);
		Dinheiro venda = new Dinheiro(12);
		Dinheiro valor = new Dinheiro(5.2);

		Produto produto = new Produto();
		produto.setPrecoDeCusto(custo);
		produto.setPrecoDeVenda(venda);
		produto.diminuirPrecoDeVenda(valor);
	}

	@Test
	public void testeAumentarLimiteMaximoDeEstoque() {
		Quantidade lmax = new Quantidade(100);
		Quantidade valor = new Quantidade(20);

		Produto produto = new Produto();
		produto.setLimiteMaximoDeEstoque(lmax);

		produto.aumentarLimiteMaximoDeEstoque(valor);

		assertEquals(new Quantidade(120), produto.getLimiteMaximoDeEstoque());
	}

	@Test
	public void testeDiminuirLimiteMaximoDeEstoque() {
		Quantidade lmax = new Quantidade(100);
		Quantidade lmin = new Quantidade(50);
		Quantidade valor = new Quantidade(20);

		Produto produto = new Produto();
		produto.setLimiteMaximoDeEstoque(lmax);
		produto.setLimiteMinimoDeEstoque(lmin);

		produto.diminuirLimiteMaximoDeEstoque(valor);

		assertEquals(new Quantidade(80), produto.getLimiteMaximoDeEstoque());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testeNaoDiminuirLimiteMaximoDeEstoque() {
		Quantidade lmax = new Quantidade(100);
		Quantidade valor = new Quantidade(120);

		Produto produto = new Produto();
		produto.setLimiteMaximoDeEstoque(lmax);

		produto.diminuirLimiteMaximoDeEstoque(valor);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testeNaoDiminuirLimiteMaximoDeEstoqueMenorQueMinimo() {
		Quantidade lmax = new Quantidade(100);
		Quantidade lmin = new Quantidade(50);

		Quantidade valor = new Quantidade(51);

		Produto produto = new Produto();
		produto.setLimiteMaximoDeEstoque(lmax);
		produto.setLimiteMinimoDeEstoque(lmin);

		produto.diminuirLimiteMaximoDeEstoque(valor);
	}

	@Test
	public void testeAumentarLimiteMinimoDeEstoque() {
		Quantidade lmin = new Quantidade(100);
		Quantidade lmax = new Quantidade(150);
		Quantidade valor = new Quantidade(30);

		Produto produto = new Produto();
		produto.setLimiteMinimoDeEstoque(lmin);
		produto.setLimiteMaximoDeEstoque(lmax);
		
		produto.aumentarLimiteMinimoDeEstoque(valor);

		assertEquals(new Quantidade(130), produto.getLimiteMinimoDeEstoque());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testeNaoAumentarLimiteMinimoDeEstoque() {
		Quantidade lmin = new Quantidade(100);
		Quantidade lmax = new Quantidade(150);
		
		Quantidade valor = new Quantidade(70);

		Produto produto = new Produto();
		produto.setLimiteMinimoDeEstoque(lmin);
		produto.setLimiteMaximoDeEstoque(lmax);

		produto.aumentarLimiteMinimoDeEstoque(valor);
	}

	@Test
	public void testeDiminuirLimiteMinimoDeEstoque() {
		Quantidade lmax = new Quantidade(100);
		Quantidade lmin = new Quantidade(50);
		Quantidade valor = new Quantidade(20);

		Produto produto = new Produto();
		produto.setLimiteMinimoDeEstoque(lmax);
		produto.setLimiteMinimoDeEstoque(lmin);

		produto.diminuirLimiteMinimoDeEstoque(valor);

		assertEquals(new Quantidade(30), produto.getLimiteMinimoDeEstoque());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testeNaoDiminuirLimiteMinimoDeEstoque() {
		Quantidade lmin = new Quantidade(100);
		Quantidade valor = new Quantidade(120);

		Produto produto = new Produto();
		produto.setLimiteMinimoDeEstoque(lmin);

		produto.diminuirLimiteMinimoDeEstoque(valor);
	}

}
