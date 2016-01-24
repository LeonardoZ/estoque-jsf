package com.leonardoz.estoque.cliente;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.leonardoz.estoque.pessoa.FiltroPessoaJuridica;

public interface ClientesJuridicos extends Serializable {

    void guardarClienteJuridico(ClienteJuridico cliente);

    void removerClienteJuridico(long idDoClienteJuridico);

    Optional<ClienteJuridico> recuperaClienteJuridico(Long idDoClienteJuridico);

    List<ClienteJuridico> recuperarClientesJuridicos();

    int quantosForamFiltrados(FiltroPessoaJuridica filtro);

    List<ClienteJuridico> filtrados(FiltroPessoaJuridica filtro);
}
