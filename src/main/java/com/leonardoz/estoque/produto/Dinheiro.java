package com.leonardoz.estoque.produto;

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

    @Column(name = "montante_bruto", scale = 2, precision = 10)
    private BigDecimal montanteBruto;

    @Transient
    private static final Locale ptBr = new Locale("pt", "BR");

    @Transient
    private static final NumberFormat moedaFormat = NumberFormat.getCurrencyInstance(ptBr); // para
											    // moedas

    public static Dinheiro deValorFormatado(String dinheiro) {
	if (dinheiro == null)
	    throw new NullPointerException(Dinheiro.mensagemDinheiroNull);
	String valorLimpo = dinheiro.replace("R$", "").replaceAll(" ", "").replace(".", "").replaceAll(",", ".");
	return new Dinheiro(valorLimpo);
    }

    public static Dinheiro deValor(double valor) {
	return new Dinheiro(valor);
    }

    public Dinheiro() {
	this.montanteBruto = configuraEscala(BigDecimal.ZERO);
    }

    private BigDecimal configuraEscala(BigDecimal valor) {
	return valor.setScale(4, RoundingMode.CEILING);
    }

    public Dinheiro(String dinheiro) {
	if (dinheiro == null)
	    throw new NullPointerException(Dinheiro.mensagemDinheiroNull);
	this.montanteBruto = configuraEscala(new BigDecimal(dinheiro));
    }

    public Dinheiro(BigDecimal rawMontante) {
	if (rawMontante == null)
	    throw new NullPointerException("Montante nao pode ser null");

	this.montanteBruto = configuraEscala(rawMontante);
    }

    public Dinheiro(double rawMontante) {
	this.montanteBruto = configuraEscala(BigDecimal.valueOf(rawMontante));
    }

    public Dinheiro(long rawMontante) {
	this.montanteBruto = configuraEscala(BigDecimal.valueOf(rawMontante));
    }

    public String valorFormatado() {
	return moedaFormat.format(this.getMontante());
    }

    public String valorFormatadoSimples() {
	return valorFormatado().replace("R$ ", "");
    }

    public Dinheiro getMontante() {
	BigDecimal novoValor = montanteBruto.setScale(DECIMAIS, RoundingMode.HALF_UP);
	return new Dinheiro(novoValor);
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

    public void setMontanteBruto(BigDecimal montanteBruto) {
	this.montanteBruto = configuraEscala(montanteBruto);
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

    public BigDecimal arredondado() {
	return montanteBruto.setScale(2, RoundingMode.CEILING);
    }

    public double doubleValue() {
	return this.montanteBruto.doubleValue();
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
	return this.montanteBruto.compareTo(dinheiro.getMontante().montanteBruto);
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
	return montanteBruto.toString();
    }

}
