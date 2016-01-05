package com.leonardoz.estoque.pessoa;

import com.leonardoz.estoque.modelo.entidade.Filtro;

public class FiltroPessoaJuridica extends Filtro {

	private static final long serialVersionUID = 1L;

	private String nomeFantasia;

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nome) {
		this.nomeFantasia = nome;
	}
	
}
