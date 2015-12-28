package com.leonardoz.estoque.pessoa.funcionario;

import java.util.List;
import java.util.Optional;

public interface Funcionarios {

	void guardarFuncionario(Funcionario funcionario);

	void removerFuncionario(long idDaFuncionario);

	Optional<Funcionario> recuperaFuncionario(Long idDaFuncionario);

	List<Funcionario> recuperarFuncionarios();

}