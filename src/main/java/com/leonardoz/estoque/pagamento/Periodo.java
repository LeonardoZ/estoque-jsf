package com.leonardoz.estoque.pagamento;

import java.util.Arrays;
import java.util.List;

public enum Periodo {

	A_VISTA(0),SEMANAL(7), QUINZENAL(15), MENSAL(30), BIMESTRAl(60), TRIMESTRAL(90), SEMESTRAL(180), ANUAL(360);

	private int dias;

	private Periodo(int dias) {
		this.dias = dias;
	}
	
	public int getDias() {
		return dias;
	}

	public String apresentavel() {
		StringBuilder builder = new StringBuilder();
		builder.append(name().substring(0, 1).toUpperCase());
		builder.append(name().substring(1).toLowerCase());
		return builder.toString().replace('_', ' ');
	}
	
	public static List<Periodo> listar(){
		return Arrays.asList(values());
	}

	
}
