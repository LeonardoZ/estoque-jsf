package com.leonardoz.estoque.fornecedor;

import java.util.List;
import java.util.Optional;

import com.leonardoz.estoque.pessoa.FiltroPessoaJuridica;

public interface Fornecedores {

    void guardarFornecedor(Fornecedor fornecedor);

    void removerFornecedor(long idDaFornecedor);

    Optional<Fornecedor> recuperaFornecedor(Long idDaFornecedor);

    List<Fornecedor> recuperarFornecedores();

    int quantosForamFiltrados(FiltroPessoaJuridica filtro);

    List<Fornecedor> filtrados(FiltroPessoaJuridica filtro);

}