package com.leonardoz.estoque.localizacao;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.valor.ValueObject;

@Embeddable
public class Cep implements ValueObject<String> {

    private static final String PADRAO = "\\d{5}[-]\\d{2}";
    private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

    @Column(name = "cep", length = 9)
    private String valor;

    public Cep() {

    }

    public Cep(String valor) {
	if (valor == null || valor.isEmpty()) {
	    throw new IllegalArgumentException("Cep não pode estar vazio.");
	}
	validarValor(valor);
	this.valor = valor;
    }

    @Size(max = 9)
    public String getValor() {
	return valor;
    }

    public void setValor(String valor) {
	this.valor = valor;
    }

    @Override
    public boolean analise(String input) {
	return avaliadorDePadrao.matcher(input).matches();
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof Cep)) {
	    return false;
	}
	Cep castOther = (Cep) other;
	return new EqualsBuilder().append(valor, castOther.valor).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(valor).toHashCode();
    }

}
