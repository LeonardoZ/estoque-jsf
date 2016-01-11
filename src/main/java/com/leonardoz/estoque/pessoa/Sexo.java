package com.leonardoz.estoque.pessoa;

public enum Sexo {

	MASCULINO("M"), FEMININO("F");

	private String sigla;

	Sexo(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

	public String apresentavel() {
		StringBuilder builder = new StringBuilder();
		builder.append(name().substring(0, 1).toUpperCase());
		builder.append(name().substring(1).toLowerCase());
		return builder.toString();
	}

	public static Sexo porSigla(String valor) {
		return valor.equals(MASCULINO.sigla) ? MASCULINO : FEMININO;
	}

}
