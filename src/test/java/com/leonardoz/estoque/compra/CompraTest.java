package com.leonardoz.estoque.compra;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.leonardoz.estoque.fornecedor.Fornecedor;
import com.leonardoz.estoque.pagamento.FormaPagamento;
import com.leonardoz.estoque.pagamento.Juros;
import com.leonardoz.estoque.pagamento.Pagamento;
import com.leonardoz.estoque.pagamento.PagamentoFactory;
import com.leonardoz.estoque.pagamento.Periodo;
import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;
import com.leonardoz.estoque.produto.Produto;
import com.leonardoz.estoque.produto.Quantidade;
import com.leonardoz.estoque.produto.QuantidadeFracionada;

public class CompraTest {

    @Test
    public void testValoresDaCompra() {
	Compra c = gerarCompra();
	
	Dinheiro valorLiquido = c.getPagamento().valorLiquido();
	Dinheiro valorProdutos = c.valorDosProdutos();
	Dinheiro valorTotal = c.valorTotal();
	
	Assert.assertEquals(Dinheiro.deValor(2400), valorProdutos);
	Assert.assertEquals(Dinheiro.deValor(2520.30), valorTotal);
	Assert.assertEquals(Dinheiro.deValor(1268.27), valorLiquido);
    }
    
    @Test
    public void testValoresDasParcelas() {
	Compra c = gerarCompra();
	
	Dinheiro valorParcelas = c.getPagamento().valorTotalParcelas();
	// pctg 10% no valor da parcela (juros simples)
	Assert.assertEquals(Dinheiro.deValor(1395.0972), valorParcelas);
    }

    private Compra gerarCompra() {
	Produto produto = new Produto();
	produto.setPrecoDeCusto(Dinheiro.deValor(240));
	produto.setDescricao("Computador CX FF-G");
	
	Compra c = new Compra();
	c.setData(new Date());
	c.setFornecedor(new Fornecedor());
	c.setNumeroNotaFiscal("18791872938172987319-2");
	c.getProdutosComprados().adicionarProduto(new ProdutoDeCompra(c, produto,new QuantidadeFracionada(10)));

	Pagamento pagamento = new PagamentoFactory()
		.forma(FormaPagamento.DINHEIRO)
		.parcelamento()
		.parcelarEm(Periodo.MENSAL, new Quantidade(4))
		.comJuros(Juros.SIMPLES, Porcentagem.de(10))
		.parcelar()
		.descontoDoValorPagamento(Porcentagem.comTaxa(0.10))
		.valorDeEntrada(Dinheiro.deValor(1000))
		.gerarPagamento();
	c.setPagamento(pagamento);
	c.setFrete(Dinheiro.deValor(120.3));
	c.configuraPagamento();
	return c;
    }

}
