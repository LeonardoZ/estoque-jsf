package com.leonardoz.estoque.produto;

import java.util.List;
import java.util.Optional;

public interface Produtos {

    void guardarProduto(Produto produto);

    void removerProduto(long idDoProduto);

    Optional<Produto> recuperarProduto(long idDoProduto);

    List<Produto> recuperarProdutos();

    int quantosForamFiltrados(FiltroProduto filtro);

    List<Produto> filtrados(FiltroProduto filtro);
}
