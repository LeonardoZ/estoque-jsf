package com.leonardoz.estoque.produto;

import java.io.Serializable;

import com.leonardoz.estoque.modelo.entidade.Filtro;

public class FiltroProduto extends Filtro implements Serializable {

    private static final long serialVersionUID = 1L;

    private String descricao;

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

}
