package com.leonardoz.estoque.pagamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;

@Entity
@Table(name = "pagamento")
public class Pagamento extends Entidade {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma", nullable = false, length = 14)
	private FormaPagamento forma;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 12)
	private StatusPagamento status;

	@Embedded
	private Parcelamento parcelamento;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "montanteBruto", column = @Column(name = "valor_pagamento", nullable = false) ) })
	private Dinheiro valorPagamento;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "montanteBruto", column = @Column(name = "entrada") ) })
	private Dinheiro entrada;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "taxaPercentual.valor", column = @Column(name = "desconto_valor_pagamento", nullable = false) ) })
	private Porcentagem descontoValorPagamento;

	@OneToMany(mappedBy = "pagamento")
	private List<Parcela> parcelas;

	public Pagamento() {
		parcelas = new ArrayList<>();
		valorPagamento = new Dinheiro(0);
		entrada = new Dinheiro(0);
		descontoValorPagamento = Porcentagem.NENHUM;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public FormaPagamento getForma() {
		return forma;
	}

	public void setForma(FormaPagamento forma) {
		this.forma = forma;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public Parcelamento getRepeticao() {
		return parcelamento;
	}

	public void setRepeticao(Parcelamento repeticao) {
		this.parcelamento = repeticao;
	}

	public Dinheiro getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(Dinheiro valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public Dinheiro getEntrada() {
		return entrada;
	}

	public void setEntrada(Dinheiro entrada) {
		this.entrada = entrada;
	}

	public Porcentagem getDescontoValorPagamento() {
		return descontoValorPagamento;
	}

	public void setDescontoValorPagamento(Porcentagem descontoValorPagamento) {
		this.descontoValorPagamento = descontoValorPagamento;
	}

	public Dinheiro valorDoPagamentoComDesconto() {
		return descontoValorPagamento.aplicarDiminuicao(valorPagamento);
	}

	public Dinheiro valorTotalParaPagar() {
		Dinheiro valorLiquido = valorPagamento;
		if (descontoValorPagamento != Porcentagem.NENHUM) {
			valorLiquido = valorDoPagamentoComDesconto();
		}
		if (entrada != null && !entrada.isZero()) {
			valorLiquido = valorLiquido.subtrair(entrada);
		}
		return valorLiquido;
	}

	public Dinheiro valorTotalParcelas() {
		return parcelas.stream()
				.map(Parcela::valorASerPago)
				.reduce(Dinheiro::somar)
				.get();
	}

	public void confirmarPagamento() {
		long parcelasAbertas = parcelas.stream().filter(p -> p.getStatus() == StatusPagamento.ABERTO).count();
		if (parcelasAbertas > 0) {
			throw new IllegalArgumentException("Existem parcelas não pagas ainda.");
		} else {
			setStatus(StatusPagamento.FINZALIZADO);
		}
	}

	public void gerarParcelas() {
		this.parcelas = parcelamento.calcularParcelas(valorTotalParaPagar(), new Date());
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

}
