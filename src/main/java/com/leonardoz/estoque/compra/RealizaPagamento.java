package com.leonardoz.estoque.compra;

import java.util.List;

import com.leonardoz.estoque.pagamento.FormaPagamento;
import com.leonardoz.estoque.pagamento.Juros;
import com.leonardoz.estoque.pagamento.Pagamento;
import com.leonardoz.estoque.pagamento.PagamentoDTO;
import com.leonardoz.estoque.pagamento.PagamentoFactory;
import com.leonardoz.estoque.pagamento.Periodo;
import com.leonardoz.estoque.pagamento.StatusPagamento;

public interface RealizaPagamento {

    default Pagamento dtoParaEntidade(PagamentoDTO dto) {
	Pagamento pagamento = new PagamentoFactory().valorPagamento(dto.getValor()).forma(dto.getForma())
		.valorDeEntrada(dto.getEntrada()).descontoDoValorPagamento(dto.getPercentualDeDesconto()).parcelamento()
		.parcelarEm(dto.getPeriodo(), dto.getQuantasVezes()).comJuros(dto.getJuros(), dto.getTaxa())
		.tendoNoVencimentoJurosAoDia(dto.getJurosVencimento(), dto.getTaxaVencimento()).parcelar()
		.gerarPagamento();
	return pagamento;
    }

    default PagamentoDTO entidadeParaDto(Pagamento pagamento) {
	PagamentoDTO dto = new PagamentoDTO();
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
	return dto;
    }

    default public List<FormaPagamento> formasDePagamento() {
	return FormaPagamento.listar();
    }

    default public List<StatusPagamento> statusDePagamento() {
	return StatusPagamento.listar();
    }

    default public List<Juros> modalidadeJuros() {
	return Juros.listar();
    }

    default public List<Periodo> periodos() {
	return Periodo.listar();
    }

}
