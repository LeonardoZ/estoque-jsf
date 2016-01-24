package com.leonardoz.estoque.modelo.entidade;

import java.io.Serializable;

public class Filtro implements Serializable {

    private static final long serialVersionUID = 1L;
    private int primeiroRegistro;
    private int quantidadeRegistros;
    private String propriedadeOrdenacao;
    private boolean ascendente;

    public int getPrimeiroRegistro() {
	return primeiroRegistro;
    }

    public void setPrimeiroRegistro(int primeiroRegistro) {
	this.primeiroRegistro = primeiroRegistro;
    }

    public int getQuantidadeRegistros() {
	return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(int quantidadeRegistros) {
	this.quantidadeRegistros = quantidadeRegistros;
    }

    public String getPropriedadeOrdenacao() {
	return propriedadeOrdenacao;
    }

    public void setPropriedadeOrdenacao(String propriedadeOrdenacao) {
	this.propriedadeOrdenacao = propriedadeOrdenacao;
    }

    public boolean isAscendente() {
	return ascendente;
    }

    public void setAscendente(boolean ascendente) {
	this.ascendente = ascendente;
    }

}
