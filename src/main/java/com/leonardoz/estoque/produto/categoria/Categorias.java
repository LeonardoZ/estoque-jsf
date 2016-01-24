package com.leonardoz.estoque.produto.categoria;

import java.util.List;
import java.util.Optional;

public interface Categorias {

    void guardarCategoria(Categoria categoria);

    void removerCategoria(long idDaCategoria);

    Optional<Categoria> recuperarCategoria(long idDaCategoria);

    List<Categoria> recuperarCategorias();

}