package com.leonardoz.estoque.pagamento;

import java.util.Arrays;
import java.util.List;

public enum Juros {

	SIMPLES,
	COMPOSTO,
	SEM_JUROS;

	private Juros() {
	}
	
	public static List<Juros> listar(){
		return Arrays.asList(values());
	}


}
