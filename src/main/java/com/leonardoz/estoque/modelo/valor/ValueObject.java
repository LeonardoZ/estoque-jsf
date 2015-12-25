package com.leonardoz.estoque.modelo.valor;

public interface ValueObject<V> {

	default void validarValor(V input) {
		if (avaliarValor(input) == false) {
			throw new IllegalArgumentException(String.format("Valor %s inválido", input.toString()));
		}
	}

	boolean avaliarValor(V input);

}
