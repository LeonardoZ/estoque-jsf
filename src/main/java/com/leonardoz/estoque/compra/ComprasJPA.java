package com.leonardoz.estoque.compra;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import com.leonardoz.estoque.infraestrutura.jpa.EspecificaCriteria;
import com.leonardoz.estoque.infraestrutura.jpa.GenericDAO;
import com.leonardoz.estoque.infraestrutura.jpa.PaginacaoDaoUtil;
import com.leonardoz.estoque.infraestrutura.jpa.Transactional;

public class ComprasJPA implements Serializable, Compras, EspecificaCriteria<FiltroCompra> {

    private static final long serialVersionUID = 1L;

    private GenericDAO<Compra> dao;
    private PaginacaoDaoUtil<Compra, FiltroCompra> paginacaoUtil;

    @Inject
    public ComprasJPA(GenericDAO<Compra> dao) {
	this.dao = dao;
	this.dao.configurarClasse(Compra.class);
	this.paginacaoUtil = new PaginacaoDaoUtil<>(dao, this);
    }

    @Override
    @Transactional
    public void guardarCompra(Compra compra) {
	dao.salvar(compra);
    }

    @Override
    @Transactional
    public void removerCompra(long idCompra) {
	dao.remover(idCompra);
    }

    @Override
    public Optional<Compra> recuperarCompra(long idCompra) {
	return dao.recuperarEntidade(idCompra);
    }

    @Override
    public List<Compra> recuperarCompras() {
	CriteriaBuilder builder = dao.getManager().getCriteriaBuilder();
	CriteriaQuery<Compra> query = builder.createQuery(Compra.class);
	Root<Compra> root = query.from(Compra.class);
	query.select(root);
	query.orderBy(builder.desc(root.get("data")));
	return dao.getManager().createQuery(query).getResultList();
    }

    @Override
    public List<Compra> filtrados(FiltroCompra filtro) {
	return paginacaoUtil.retornaFiltrados(filtro);
    }

    @Override
    public int quantosForamFiltrados(FiltroCompra filtro) {
	return paginacaoUtil.quantidadeFiltrados(filtro);
    }

    public BiFunction<FiltroCompra, Criteria, Criteria> especificadorDeCriteria() {
	return (filtro, criteria) -> {
	    return criteria;
	};
    }

}
