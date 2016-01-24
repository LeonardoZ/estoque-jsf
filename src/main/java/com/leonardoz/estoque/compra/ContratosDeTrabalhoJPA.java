package com.leonardoz.estoque.compra;

import com.leonardoz.estoque.cliente.ContratoDeTrabalho;
import com.leonardoz.estoque.infraestrutura.jpa.GenericDAO;
import com.leonardoz.estoque.infraestrutura.jpa.Transactional;

public class ContratosDeTrabalhoJPA implements ContratosDeTrabalho {

    private GenericDAO<ContratoDeTrabalho> dao;

    public ContratosDeTrabalhoJPA(GenericDAO<ContratoDeTrabalho> dao) {
	this.dao = dao;
    }

    @Override
    @Transactional
    public void salvaContrato(ContratoDeTrabalho cdt) {
	dao.salvar(cdt);
    }

}
