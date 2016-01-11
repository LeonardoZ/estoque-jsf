package com.leonardoz.estoque.pagamento;

import java.util.Arrays;
import java.util.List;

public enum StatusPagamento {

	ABERTO("Aberto"), FINZALIZADO("Finalizado"), CANCELADO("Cancelado");

	private String descricao;

	private StatusPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static List<StatusPagamento> listar(){
		return Arrays.asList(values());
	}

}
