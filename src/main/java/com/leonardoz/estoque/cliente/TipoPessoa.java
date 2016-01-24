package com.leonardoz.estoque.cliente;

import java.util.Arrays;
import java.util.List;

public enum TipoPessoa {

    FISICA("Pessoa Física"), JURIDICA("Pessoa Jurídica");

    private String pessoa;

    TipoPessoa(String pessoa) {
	this.pessoa = pessoa;
    }

    public String getPessoa() {
	return pessoa;
    }

    public void setPessoa(String pessoa) {
	this.pessoa = pessoa;
    }

    public static List<TipoPessoa> listar() {
	return Arrays.asList(values());
    }

    @Override
    public String toString() {
	return pessoa;
    }

    public static TipoPessoa recuperaPorValor(String valor) {
	if (valor.equals(FISICA.getPessoa())) {
	    return FISICA;
	} else if (valor.equals(JURIDICA.getPessoa())) {
	    return JURIDICA;
	} else {
	    return null;
	}
    }

}
