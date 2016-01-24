package com.leonardoz.estoque.cliente;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.leonardoz.estoque.pessoa.FiltroPessoaFisica;

public interface ClientesFisicos extends Serializable {

    void guardarClienteFisico(ClienteFisico cliente);

    void removerClienteFisico(long idDoClienteFisico);

    Optional<ClienteFisico> recuperaClienteFisico(Long idDoClienteFisico);

    List<ClienteFisico> recuperarClientesFisicos();

    int quantosForamFiltrados(FiltroPessoaFisica filtro);

    List<ClienteFisico> filtrados(FiltroPessoaFisica filtro);
}
