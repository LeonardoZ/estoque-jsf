package com.leonardoz.estoque.compra;

import java.util.List;
import java.util.Optional;

public interface Compras {

    void guardarCompra(Compra produto);

    void removerCompra(long idCompra);

    Optional<Compra> recuperarCompra(long idCompra);

    List<Compra> recuperarCompras();

    int quantosForamFiltrados(FiltroCompra filtro);

    List<Compra> filtrados(FiltroCompra filtro);
}
