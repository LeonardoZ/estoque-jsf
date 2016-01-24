package com.leonardoz.estoque.pagamento;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;

@Entity
@Table(name = "parcela")
public class Parcela extends Entidade {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "vencimento")
    private Date dataVencimento;

    @Temporal(TemporalType.DATE)
    @Column(name = "pago_em")
    private Date pagoEm;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "montanteBruto", column = @Column(name = "valor") ) })
    private Dinheiro valor;

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "taxaPercentual.valor", column = @Column(name = "taxa_desconto") ) })
    private Porcentagem desconto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 12)
    private StatusPagamento status;

    @ManyToOne
    @JoinColumn(name = "pagamento_id", nullable = false)
    private Pagamento pagamento;

    public Parcela() {

    }

    Parcela(Date dataVencimento, Dinheiro valor, Porcentagem desconto) {
	super();
	this.dataVencimento = dataVencimento;
	this.valor = valor;
	this.desconto = desconto;
	this.status = StatusPagamento.ABERTO;
    }

    public Dinheiro getValor() {
	return valor;
    }

    public void setValor(Dinheiro valor) {
	this.valor = valor;
    }

    public StatusPagamento getStatus() {
	return status;
    }

    public void setStatus(StatusPagamento status) {
	this.status = status;
    }

    public Date getDataVencimento() {
	return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
	this.dataVencimento = dataVencimento;
    }

    public Date getPagoEm() {
	return pagoEm;
    }

    public void setPagoEm(Date pagoEm) {
	this.pagoEm = pagoEm;
    }

    public Porcentagem getDesconto() {
	return desconto;
    }

    public void setDesconto(Porcentagem desconto) {
	this.desconto = desconto;
    }

    public Pagamento getPagamento() {
	return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
	this.pagamento = pagamento;
    }

    public Dinheiro valorASerPago() {
	if (status != StatusPagamento.ABERTO)
	    return Dinheiro.ZERO;
	return calcularValorLiquido();
    }

    public Dinheiro valorPago() {
	if (status == StatusPagamento.ABERTO)
	    return Dinheiro.ZERO;
	return calcularValorLiquido();
    }

    private Dinheiro calcularValorLiquido() {
	Dinheiro valorLiquido = valor;
	// calcula descontos manuais
	if (desconto != Porcentagem.NENHUM) {
	    valorLiquido = desconto.aplicarDiminuicao(valorLiquido);
	}
	// calcula valor da parcela após o vencimento
	if (pagoEm != null && pagoEm.after(dataVencimento)) {
	    valorLiquido = calcularValorAposVencimento(getPagamento().getParcelamento(), valorLiquido);
	}
	return valorLiquido;
    }

    private Dinheiro calcularValorAposVencimento(Parcelamento parcelamento, Dinheiro valorLiquido) {
	Juros jurosVencimento = parcelamento.getJurosVencimento();
	Porcentagem taxaVencimento = parcelamento.getTaxaDeJurosDeVencimento();

	Calendar calendar = Calendar.getInstance();
	calendar.setTime(dataVencimento);
	int d = calendar.get(Calendar.DAY_OF_MONTH);
	int m = calendar.get(Calendar.MONTH);
	int a = calendar.get(Calendar.YEAR);
	LocalDate vencimentoConvertido = LocalDate.of(a, ++m, d);

	calendar.setTime(pagoEm);
	int d2 = calendar.get(Calendar.DAY_OF_MONTH);
	int m2 = calendar.get(Calendar.MONTH);
	int a2 = calendar.get(Calendar.YEAR);
	LocalDate pagamentooConvertido = LocalDate.of(a2, ++m2, d2);

	Period between = Period.between(vencimentoConvertido, pagamentooConvertido);
	int dias = between.getDays();

	switch (jurosVencimento) {
	case COMPOSTO:
	    valorLiquido = calcularVencimentoJurosComposto(dias, taxaVencimento, valorLiquido);
	    break;
	case SIMPLES:
	    valorLiquido = calcularVencimentoJurosSimples(dias, taxaVencimento, valorLiquido);
	    break;
	default:
	    break;
	}
	return valorLiquido;
    }

    private Dinheiro calcularVencimentoJurosSimples(int dias, Porcentagem taxaVencimento, Dinheiro valorLiquido) {
	// vl = 10,00
	// j = 1% a. d.
	// 10d
	// vl = 11,00
	Dinheiro taxaMultiplicadaPorDias = taxaVencimento.calcularPercentualDe(valorLiquido).multiplicar(dias);
	return valorLiquido.somar(taxaMultiplicadaPorDias);
    }

    private Dinheiro calcularVencimentoJurosComposto(int dias, Porcentagem taxaVencimento, Dinheiro valorLiquido) {
	for (int i = 0; i < dias; i++) {
	    valorLiquido = taxaVencimento.aplicarAumento(valorLiquido);
	}
	return valorLiquido;
    }

    public void confirmarPaga(Dinheiro valor) {
	if (valor.isMenor(valorASerPago())) {
	    throw new IllegalArgumentException("Valor insuficiente para quitar a parcela.");
	}
	setStatus(StatusPagamento.FINZALIZADO);
    }

    public static Parcela de(LocalDate dataVencimento, Dinheiro valor) {
	int ano = dataVencimento.getYear();
	int mes = dataVencimento.getMonthValue();
	int dia = dataVencimento.getDayOfMonth();

	Date data = new Calendar.Builder().setDate(ano, mes, dia).build().getTime();

	return new Parcela(data, valor, Porcentagem.NENHUM);
    }

    @Override
    public String toString() {
	return "Parcela [dataVencimento=" + dataVencimento + ", valor=" + valor + ", desconto=" + desconto + ", status="
		+ status + "]";
    }

}
