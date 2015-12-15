package com.leonardoz.estoque.model.values;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/*
 * Obrigado aos membros Flavio Almeida e Ataxexe do GUJ.
 * 
 * Fiz modificações para adequar ao meu projeto.
 */
@Embeddable
public class Dinheiro extends Number implements Serializable, Comparable<Dinheiro> {

	private static final long serialVersionUID = 1L;

	private static final String mensagemDinheiroNull = "Dinheiro não pode ser nulo";

	private static MathContext CONTEXTO = new MathContext(15, RoundingMode.HALF_UP);

	private static int DECIMAIS = 2;
	private static int MENOR = -1;
	private static int IGUAL = 0;
	private static int MAIOR = 1;

	public static Dinheiro ZERO = new Dinheiro(0.00);
	
	@Column(name="montante_bruto", nullable = false, scale = 2, precision = 10)
	private BigDecimal montanteBruto;

	@Transient
	private Locale ptBr = new Locale("pt", "BR");

	@Transient
	private NumberFormat moedaFormat = NumberFormat.getCurrencyInstance(ptBr); // para moedas

	protected Dinheiro() {
	}
	
	public Dinheiro(String dinheiro) {
		if (dinheiro == null)
			throw new NullPointerException(Dinheiro.mensagemDinheiroNull);
		this.montanteBruto = new BigDecimal(dinheiro);
	}

	public Dinheiro(BigDecimal rawMontante) {
		if (rawMontante == null)
			throw new NullPointerException("Montante nao pode ser null");

		this.montanteBruto = rawMontante;
	}

	public Dinheiro(double rawMontante) {

		this.montanteBruto = BigDecimal.valueOf(rawMontante);
	}

	public Dinheiro(long rawMontante) {
		this.montanteBruto = BigDecimal.valueOf(rawMontante);
	}

	public String valorFormatado() {

		return moedaFormat.format(this.getMontante());
	}

	public Dinheiro getMontante() {
		return new Dinheiro(this.montanteBruto.setScale(DECIMAIS, RoundingMode.HALF_UP));
	}

	public BigDecimal getMontanteBruto() {
		return this.montanteBruto;
	}

	public Dinheiro somar(Dinheiro valorSoma) {
		if (valorSoma == null)
			throw new NullPointerException(Dinheiro.mensagemDinheiroNull);
		return new Dinheiro(this.montanteBruto.add(valorSoma.getMontanteBruto(), CONTEXTO));
	}

	public Dinheiro subtrair(Dinheiro valorSubtrair) {
		if (valorSubtrair == null)
			throw new NullPointerException(Dinheiro.mensagemDinheiroNull);
		return new Dinheiro(this.montanteBruto.subtract(valorSubtrair.getMontanteBruto(), CONTEXTO));
	}

	public Dinheiro multiplicar(long valorMultiplicador) {
		return new Dinheiro(this.montanteBruto.multiply(BigDecimal.valueOf(valorMultiplicador), CONTEXTO));
	}

	public Dinheiro multiplicar(double valorMultiplicador) {
		return new Dinheiro(this.montanteBruto.multiply(BigDecimal.valueOf(valorMultiplicador), CONTEXTO));
	}

	public Dinheiro dividir(long valorDivisor) {
		return new Dinheiro(this.montanteBruto.divide(BigDecimal.valueOf(valorDivisor), CONTEXTO));
	}

	public Dinheiro dividir(double valorDivisor) {
		return new Dinheiro(this.montanteBruto.divide(BigDecimal.valueOf(valorDivisor), CONTEXTO));
	}

	public boolean isZero() {
		return this.getMontante().montanteBruto.compareTo(BigDecimal.ZERO) == IGUAL;
	}

	public boolean isMaior(Dinheiro dinheiro) {
		return this.getMontante().compareTo(dinheiro.getMontante()) == MAIOR;
	}

	public boolean isMenor(Dinheiro dinheiro) {
		return this.getMontante().compareTo(dinheiro.getMontante()) == MENOR;
	}

	public boolean isMaiorIgual(Dinheiro dinheiro) {
		return (this.getMontante().compareTo(dinheiro.getMontante()) == MAIOR
				|| this.getMontante().compareTo(dinheiro.getMontante()) == IGUAL);
	}

	public boolean isMenorIgual(Dinheiro dinheiro) {
		return (this.getMontante().compareTo(dinheiro.getMontante()) == MENOR
				|| this.getMontante().compareTo(dinheiro.getMontante()) == IGUAL);
	}

	public Dinheiro percentual(double perc) {

		return new Dinheiro(this.montanteBruto.multiply(BigDecimal.valueOf(perc), CONTEXTO)
				.divide(BigDecimal.valueOf(100), CONTEXTO));

	}

	public int dividirParaInteiro(double divisor) {
		return this.montanteBruto.divideToIntegralValue(BigDecimal.valueOf(divisor)).intValue();
	}

	public int dividirParaInteiro(long divisor) {
		return this.montanteBruto.divideToIntegralValue(BigDecimal.valueOf(divisor)).intValue();
	}

	public int dividirParaInteiro(BigDecimal divisor) {
		return this.montanteBruto.divideToIntegralValue(divisor).intValue();

	}

	public long dividirParaLong(double divisor) {
		return this.montanteBruto.divideToIntegralValue(BigDecimal.valueOf(divisor)).longValue();
	}

	public long dividirParaLong(long divisor) {
		return this.montanteBruto.divideToIntegralValue(BigDecimal.valueOf(divisor)).longValue();
	}

	public long dividirParaLong(BigDecimal divisor) {
		return this.montanteBruto.divideToIntegralValue(divisor).longValue();

	}

	public double doubleValue() {
		return this.getMontante().doubleValue();
	}

	public long longValue() {
		return this.getMontante().longValue();
	}

	@Override
	public int intValue() {
		return this.getMontante().intValue();
	}

	@Override
	public float floatValue() {
		return this.getMontante().floatValue();
	}

	public Dinheiro inverter() {
		return new Dinheiro(this.getMontanteBruto().negate(Dinheiro.CONTEXTO));
	}

	public Dinheiro max(Dinheiro dinheiro) {
		return new Dinheiro(this.getMontante().montanteBruto.max(dinheiro.getMontante().montanteBruto));
	}

	public Dinheiro min(Dinheiro dinheiro) {
		return new Dinheiro(this.getMontante().montanteBruto.min(dinheiro.getMontante().montanteBruto));
	}

	@Override
	public int compareTo(Dinheiro dinheiro) {
		if (dinheiro == null)
			throw new NullPointerException(Dinheiro.mensagemDinheiroNull);
		return this.getMontante().compareTo(dinheiro.getMontante());
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Dinheiro)) {
			return false;
		}
		Dinheiro castOther = (Dinheiro) other;
		return new EqualsBuilder().append(montanteBruto, castOther.montanteBruto).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(montanteBruto).toHashCode();
	}

	@Override
	public String toString() {
		return this.getMontante().toString();
	}

}
