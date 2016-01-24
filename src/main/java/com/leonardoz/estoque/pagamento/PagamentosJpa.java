package com.leonardoz.estoque.pagamento;

import javax.inject.Inject;

import com.leonardoz.estoque.infraestrutura.jpa.GenericDAO;
import com.leonardoz.estoque.infraestrutura.jpa.Transactional;

public class PagamentosJpa implements Pagamentos {

    private GenericDAO<Pagamento> dao;

    @Inject
    public PagamentosJpa(GenericDAO<Pagamento> dao) {
	this.dao = dao;
    }

    @Override
    @Transactional
    public void registraPagamento(Pagamento pagamento) {
	dao.salvar(pagamento);
    }

}
