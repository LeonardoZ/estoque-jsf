package com.leonardoz.estoque.funcionario;

import java.util.List;
import java.util.Optional;

import com.leonardoz.estoque.pessoa.FiltroPessoaFisica;

public interface Funcionarios {

    void guardarFuncionario(Funcionario funcionario);

    void removerFuncionario(long idDaFuncionario);

    Optional<Funcionario> recuperaFuncionario(Long idDaFuncionario);

    List<Funcionario> recuperarFuncionarios();

    int quantosForamFiltrados(FiltroPessoaFisica filtro);

    List<Funcionario> filtrados(FiltroPessoaFisica filtro);
}