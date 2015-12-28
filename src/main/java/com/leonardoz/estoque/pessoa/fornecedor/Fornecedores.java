package com.leonardoz.estoque.pessoa.fornecedor;

import java.util.List;
import java.util.Optional;

public interface Fornecedores {

	void guardarFornecedor(Fornecedor fornecedor);

	void removerFornecedor(long idDaFornecedor);

	Optional<Fornecedor> recuperaFornecedor(Long idDaFornecedor);

	List<Fornecedor> recuperarFornecedores();

}