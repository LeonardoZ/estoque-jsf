package com.leonardoz.estoque.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class QuantidadeFracionada implements Serializable, Comparable<QuantidadeFracionada> {

	private static final long serialVersionUID = 1L;

	@Column(name = "quantidade")
	private BigDecimal valor;

	protected QuantidadeFracionada() {
	}

	public QuantidadeFracionada(BigDecimal valor) {
		super();
		validarValor(valor);
		this.valor = configuraEscala(valor);
	}

	public QuantidadeFracionada(double valor) {
		super();
		BigDecimal novoValor = BigDecimal.valueOf(valor);
		validarValor(novoValor);
		this.valor = configuraEscala(novoValor);

	}

	private BigDecimal configuraEscala(BigDecimal valor) {
		return valor.setScale(4, RoundingMode.CEILING);
	}

	public void validarValor(BigDecimal valor) {
		if (valor.doubleValue() < 0l) {
			throw new IllegalArgumentException("Quantia não pode ser negativa");
		}
	}

	public void setValor(BigDecimal valor) {
		this.valor = configuraEscala(valor);
	}

	public QuantidadeFracionada aumentar(BigDecimal valorParaAdicionar) {
		validarValor(valorParaAdicionar);
		return new QuantidadeFracionada(valor.add(valorParaAdicionar));
	}

	public QuantidadeFracionada aumentar(double valorParaAdicionar) {
		return aumentar(BigDecimal.valueOf(valorParaAdicionar));
	}

	public QuantidadeFracionada diminuir(BigDecimal valorParaSubtrair) {
		validarValor(valorParaSubtrair);
		if (valorParaSubtrair.compareTo(valor) == 1) {
			throw new IllegalArgumentException("Valor para diminuir maior do que a quantidade disposnível.");
		}
		return new QuantidadeFracionada(valor.subtract(valorParaSubtrair));
	}

	public QuantidadeFracionada diminuir(double valorParaSubtrair) {
		return diminuir(BigDecimal.valueOf(valorParaSubtrair));
	}

	public BigDecimal getValor() {
		return BigDecimal.valueOf(valor.doubleValue());
	}

	@Override
	public int compareTo(QuantidadeFracionada o) {
		return valor.compareTo(o.valor);
	}

	public QuantidadeFracionada multiplicar(QuantidadeFracionada taxaPercentual) {
		return new QuantidadeFracionada(valor.multiply(taxaPercentual.valor));
	}

	public QuantidadeFracionada dividir(QuantidadeFracionada divisorCentisimal) {
		return new QuantidadeFracionada(valor.divide(divisorCentisimal.valor));
	}

	public boolean isMaiorIgual(QuantidadeFracionada qtd) {
		int compareTo = this.valor.compareTo(qtd.valor);
		return compareTo == 1 || compareTo == 0;
	}

	public boolean isMaior(QuantidadeFracionada qtd) {
		int compareTo = this.valor.compareTo(qtd.valor);
		return compareTo == 1;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof QuantidadeFracionada)) {
			return false;
		}
		QuantidadeFracionada castOther = (QuantidadeFracionada) other;
		return new EqualsBuilder().append(valor, castOther.valor).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(valor).toHashCode();
	}

	@Override
	public String toString() {
		return valor.toString();
	}

}
