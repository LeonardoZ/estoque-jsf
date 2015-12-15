package com.leonardoz.estoque.model.values;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class Quantidade implements Serializable, Comparable<Quantidade> {

	private static final long serialVersionUID = 1L;

	@Column(name = "quantidade", nullable = false)
	private BigInteger valor;
	
	protected Quantidade() {

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

	public void validarValor(BigInteger valor) {
		if (valor.longValue() < 0l) {
			throw new IllegalArgumentException("Quantia não pode ser negativa");
		}
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

	public BigInteger getValor() {
		return BigInteger.valueOf(valor.longValue());
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

}
