package com.leonardoz.estoque.pessoa;

import com.leonardoz.estoque.modelo.entidade.Filtro;

public class FiltroPessoaFisica extends Filtro {

    private static final long serialVersionUID = 1L;

    private String nome;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

}
