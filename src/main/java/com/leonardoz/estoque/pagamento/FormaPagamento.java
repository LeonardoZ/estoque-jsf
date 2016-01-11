package com.leonardoz.estoque.pagamento;

import java.util.Arrays;
import java.util.List;

public enum FormaPagamento {

	BOLETO("Boleto"), CARTAO_CREDITO("Cartão de Crédito"), DINHEIRO("Dinheiro"), DEPOSITO("Deposito");

	private String descricao;

	private FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static List<FormaPagamento> listar() {
		return Arrays.asList(values());
	}

}
