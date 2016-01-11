package com.leonardoz.estoque.compra;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import com.leonardoz.estoque.produto.Dinheiro;

@Embeddable
public class ProdutosComprados {

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "compra")
	private List<ProdutoDeCompra> produtos;

	public ProdutosComprados() {
		produtos = new LinkedList<>();
	}

	public void adicionarProduto(ProdutoDeCompra produto) {
		produtos.add(produto);
	}

	public void removerProduto(ProdutoDeCompra produto) {
		produtos.remove(produto);
	}

	public Dinheiro precoTotal() {
		return produtos.stream().map(ProdutoDeCompra::getValorDeCompra).reduce(Dinheiro::somar).get();
	}

	protected List<ProdutoDeCompra> getProdutos() {
		return produtos;
	}

	protected void setProdutos(List<ProdutoDeCompra> produtos) {
		this.produtos = produtos;
	}

}
