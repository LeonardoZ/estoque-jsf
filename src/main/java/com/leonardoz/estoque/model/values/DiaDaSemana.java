package com.leonardoz.estoque.model.values;

import java.util.Arrays;
import java.util.List;

public enum DiaDaSemana {

	DOMINGO("Domingo"), SEGUNDA("Segunda"), TERCA("Terça"), QUARTA("Quarta"), QUINTA("Quinta"), SEXTA("Sexta"), SABADO(
			"Sábado");

	private String valor;

	private DiaDaSemana(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setSigla(String valor) {
		this.valor = valor;
	}

	public static List<DiaDaSemana> listar() {
		return Arrays.asList(values());
	}
	
	public static DiaDaSemana recuperaPorValor(String valor){
		DiaDaSemana[] dias = values();
		for (int i = 0; i < dias.length; i++) {
			DiaDaSemana dia = dias[i];
			if(dia.valor.equals(valor)){
				return dia;
			}
		}
		throw new IllegalArgumentException("Nenhum dia encontrado com o valor especificado.");
	}
	
	@Override
	public String toString() {
		return valor;
	}

}
