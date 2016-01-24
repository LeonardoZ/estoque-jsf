package com.leonardoz.estoque.modelo.valor;

public interface ValueObject<V> {

    default void validarValor(V valor) {
	if (analise(valor) == false) {
	    throw new IllegalArgumentException(String.format("Valor %s inválido", valor.toString()));
	}
    }

    boolean analise(V valor);

}
