package com.leonardoz.estoque.modelo.valor;

public interface StringValueObject extends ValueObject<String> {

    default void estaVazio(String valor) {
	if (valor == null || valor.isEmpty()) {
	    throw new IllegalArgumentException("Valor não pode estar vazio.");
	}
    }

    @Override
    default void validarValor(String valor) {
	estaVazio(valor);
	ValueObject.super.validarValor(valor);
    }

    boolean analise(String valor);

}
