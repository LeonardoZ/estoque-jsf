package com.leonardoz.estoque.pagamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Porcentagem;
import com.leonardoz.estoque.produto.Quantidade;

@Named
@ViewScoped
public class EfetuaPagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pagamento pagamento;
	private PagamentoDTO dto;
	private static List<Pagamento> pagamentos = new ArrayList<>();

	{
		Pagamento pagamento = new PagamentoFactory().forma(FormaPagamento.DINHEIRO)
				.descontoDoValorPagamento(Porcentagem.NENHUM).parcelamento()
				.parcelarEm(Periodo.QUINZENAL, new Quantidade(10)).comJuros(Juros.SIMPLES, Porcentagem.de(10))
				.tendoNoVencimentoJurosAoDia(Juros.SIMPLES, Porcentagem.de(1)).parcelar()
				.valorPagamento(new Dinheiro(2000)).gerarPagamento();
		pagamentos.add(pagamento);

	}

	@PostConstruct
	public void iniciar() {
		if (pagamento == null)
			pagamento = new Pagamento();
		if (dto == null)
			dto = new PagamentoDTO();
	}

	public void registraPagamento() {
		pagamento = new PagamentoFactory().valorPagamento(dto.getValor()).forma(dto.getForma())
				.valorDeEntrada(dto.getEntrada()).descontoDoValorPagamento(dto.getPercentualDeDesconto()).parcelamento()
				.parcelarEm(dto.getPeriodo(), dto.getQuantasVezes()).comJuros(dto.getJuros(), dto.getTaxa())
				.tendoNoVencimentoJurosAoDia(dto.getJurosVencimento(), dto.getTaxaVencimento()).parcelar()
				.gerarPagamento();
		pagamentos.add(pagamento);
	}

	public void aoSelecionarPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
		dto.setEntrada(pagamento.getEntrada());
		dto.setForma(pagamento.getForma());
		dto.setValor(pagamento.getValorPagamento());
		dto.setPercentualDeDesconto(pagamento.getDescontoValorPagamento());
		dto.setPeriodo(pagamento.getParcelamento().getPeriodo());
		dto.setJuros(pagamento.getParcelamento().getJuros());
		dto.setJurosVencimento(pagamento.getParcelamento().getJurosVencimento());
		dto.setTaxa(pagamento.getParcelamento().getTaxa());
		dto.setTaxaVencimento(pagamento.getParcelamento().getTaxaDeJurosDeVencimento());
		dto.setQuantasVezes(pagamento.getParcelamento().getQuantasVezes());

	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public List<FormaPagamento> formasDePagamento() {
		return FormaPagamento.listar();
	}

	public List<StatusPagamento> statusDePagamento() {
		return StatusPagamento.listar();
	}

	public List<Juros> modalidadeJuros() {
		return Juros.listar();
	}

	public List<Periodo> periodos() {
		return Periodo.listar();
	}

	public PagamentoDTO getDto() {
		return dto;
	}

	public void setDto(PagamentoDTO dto) {
		this.dto = dto;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
}
