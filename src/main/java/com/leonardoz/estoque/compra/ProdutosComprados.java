package com.leonardoz.estoque.compra;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import com.leonardoz.estoque.produto.Dinheiro;

@Embeddable
public class ProdutosComprados implements Serializable {

    private static final long serialVersionUID = 1L;

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
	return produtos.stream().map(ProdutoDeCompra::valorTotal).reduce(Dinheiro::somar)
		.orElseGet(() -> Dinheiro.ZERO);
    }

    public List<ProdutoDeCompra> getProdutos() {
	return produtos;
    }

    public void setProdutos(List<ProdutoDeCompra> produtos) {
	this.produtos = produtos;
    }

}
