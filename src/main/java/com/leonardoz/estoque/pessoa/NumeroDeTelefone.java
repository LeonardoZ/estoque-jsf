package com.leonardoz.estoque.pessoa;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.valor.StringValueObject;

@Embeddable
public class NumeroDeTelefone implements StringValueObject {

    private static final String PADRAO =
    // (99) 91212-[]
    "^(\\([1-9][1-9]\\) [0-9][0-9]{4}-[0-9]{4})" + "|(\\([1-9][1-9]\\) [0-9]{4}-[0-9]{4})$";
    private static final Pattern avaliadorDePadrao = Pattern.compile(PADRAO);

    @Size(max = 17)
    @Column(name = "telefone", length = 17)
    private String valor;

    public NumeroDeTelefone() {

    }

    public NumeroDeTelefone(String valor) {
	validarValor(valor);
	setValor(valor);
    }

    public String getValor() {
	return valor;
    }

    public void setValor(String valor) {
	this.valor = valor;
    }

    @Override
    public boolean analise(String valor) {
	return avaliadorDePadrao.matcher(valor).matches();
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof NumeroDeTelefone)) {
	    return false;
	}
	NumeroDeTelefone castOther = (NumeroDeTelefone) other;
	return new EqualsBuilder().append(valor, castOther.valor).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(valor).toHashCode();
    }

}
