package com.leonardoz.estoque.produto.unidade;

import java.util.List;
import java.util.Optional;

public interface UnidadesDeMedida {

    void guardarUnidadeDeMedida(UnidadeDeMedida unidade);

    void removerUnidadeDeMedida(long idDaUnidadeDeMedida);

    Optional<UnidadeDeMedida> recuperaUnidadeDeMedida(Long idDaUnidadeDeMedida);

    List<UnidadeDeMedida> recuperarUnidadeDeMedidas();

}