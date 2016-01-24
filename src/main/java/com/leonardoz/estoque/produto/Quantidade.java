package com.leonardoz.estoque.produto;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.valor.ValueObject;

@Embeddable
public class Quantidade implements Serializable, Comparable<Quantidade>, ValueObject<BigInteger> {

    private static final long serialVersionUID = 1L;

    @Column(name = "quantidade")
    private BigInteger valor;

    protected Quantidade() {
	valor = BigInteger.ZERO;
    }

    public Quantidade(BigInteger valor) {
	super();
	validarValor(valor);
	this.valor = valor;
    }

    public Quantidade(long valor) {
	super();
	BigInteger novoValor = BigInteger.valueOf(valor);
	validarValor(novoValor);
	this.valor = novoValor;
    }

    public void setValor(BigInteger valor) {
	this.valor = valor;
    }

    public static Quantidade deValorFormatado(String valor) {
	String valorLimpo = valor.replace(".", "").replaceAll(",", ".");
	return new Quantidade(new BigInteger(valorLimpo));
    }

    @Override
    public boolean analise(BigInteger valor) {
	return valor.longValue() > -1;
    }

    public Quantidade aumentar(Quantidade valorParaAdicionar) {
	return aumentar(valorParaAdicionar.getValor());
    }

    public Quantidade aumentar(BigInteger valorParaAdicionar) {
	validarValor(valorParaAdicionar);
	return new Quantidade(valor.add(valorParaAdicionar));
    }

    public Quantidade aumentar(long valorParaAdicionar) {
	return aumentar(BigInteger.valueOf(valorParaAdicionar));
    }

    public Quantidade diminuir(BigInteger valorParaSubtrair) {
	validarValor(valorParaSubtrair);
	if (valorParaSubtrair.compareTo(valor) == 1) {
	    throw new IllegalArgumentException("Valor para diminuir maior do que a quantidade disposnível.");
	}
	return new Quantidade(valor.subtract(valorParaSubtrair));
    }

    public Quantidade diminuir(long valorParaSubtrair) {
	return diminuir(BigInteger.valueOf(valorParaSubtrair));
    }

    public Quantidade diminuir(Quantidade qtd) {
	return diminuir(qtd.valor);
    }

    public BigInteger getValor() {
	return BigInteger.valueOf(valor.longValue());
    }

    public boolean isMaiorIgual(Quantidade qtd) {
	int compareTo = this.valor.compareTo(qtd.valor);
	return compareTo == 1 || compareTo == 0;
    }

    public boolean isMaior(Quantidade qtd) {
	int compareTo = this.valor.compareTo(qtd.valor);
	return compareTo == 1;
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof Quantidade)) {
	    return false;
	}
	Quantidade castOther = (Quantidade) other;
	return new EqualsBuilder().append(valor, castOther.valor).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(valor).toHashCode();
    }

    @Override
    public int compareTo(Quantidade o) {
	return valor.compareTo(o.valor);
    }

    @Override
    public String toString() {
	return valor.toString();
    }

    public Quantidade elevadaA(Quantidade n) {
	return new Quantidade(valor.pow(n.getValor().intValue()));
    }

}
