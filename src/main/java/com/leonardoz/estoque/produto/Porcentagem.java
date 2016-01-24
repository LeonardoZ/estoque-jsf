package com.leonardoz.estoque.produto;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Porcentagem {

    static class Nenhum extends Porcentagem {

    }

    private final static QuantidadeFracionada DIVISOR_CENTISIMAL = new QuantidadeFracionada(100);

    public static final Porcentagem NENHUM = new Nenhum();

    @Embedded
    private QuantidadeFracionada taxaPercentual;

    public Porcentagem() {
	taxaPercentual = new QuantidadeFracionada(0);
    }

    public QuantidadeFracionada getTaxaPercentual() {
	return taxaPercentual;
    }

    public void setTaxaPercentual(QuantidadeFracionada taxaPercentual) {
	this.taxaPercentual = taxaPercentual;
    }

    /*
     * 0,1 = 10% 1,0 = 100%
     */
    public static Porcentagem comTaxa(QuantidadeFracionada taxa) {
	Porcentagem porcentagem = new Porcentagem();
	porcentagem.setTaxaPercentual(taxa);
	return porcentagem;
    }

    public static Porcentagem comTaxa(double taxa) {
	return comTaxa(new QuantidadeFracionada(taxa));
    }

    /*
     * 100% = 0,1 50% = 0,5
     */
    public static Porcentagem comPorcentagemDe(QuantidadeFracionada taxa) {
	Porcentagem porcentagem = new Porcentagem();
	porcentagem.setTaxaPercentual(taxa.dividir(DIVISOR_CENTISIMAL));
	return porcentagem;
    }

    public static Porcentagem de(double porcentagem) {
	return comPorcentagemDe(new QuantidadeFracionada(porcentagem));
    }

    public Dinheiro calcularPercentualDe(Dinheiro valor) {
	return valor.multiplicar(taxaPercentual.getValor().doubleValue());
    }

    public Dinheiro aplicarAumento(Dinheiro valor) {
	return valor.multiplicar(taxaPercentual.aumentar(1.0).getValor().doubleValue());
    }

    public Dinheiro aplicarDiminuicao(Dinheiro valor) {
	if (taxaPercentual.isMaior(new QuantidadeFracionada(100))) {
	    throw new IllegalArgumentException("Impossível diminuir mais do que 100% de um valor.");
	}
	Dinheiro resultado = calcularPercentualDe(valor);
	return valor.subtrair(resultado);
    }

    public String percentual() {
	String num = taxaPercentual.multiplicar(DIVISOR_CENTISIMAL).toString();
	return new StringBuilder().append(num).append("%").toString();
    }

    @Override
    public String toString() {
	return taxaPercentual.toString();
    }

}
