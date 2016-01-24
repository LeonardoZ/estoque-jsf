package com.leonardoz.estoque.funcionario;

import java.util.Arrays;
import java.util.List;

public enum Habilitacao {

    A, B, AB, C, AC, D, AD, E, AE, NENHUMA;

    public String apresentavel() {
	StringBuilder builder = new StringBuilder();
	builder.append(name().substring(0, 1).toUpperCase());
	builder.append(name().substring(1).toLowerCase());
	return builder.toString();
    }

    public static List<Habilitacao> listar() {
	return Arrays.asList(values());
    }

}
