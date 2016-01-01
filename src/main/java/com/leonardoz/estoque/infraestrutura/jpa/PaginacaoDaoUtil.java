package com.leonardoz.estoque.infraestrutura.jpa;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.modelo.entidade.Filtro;

public class PaginacaoDaoUtil<T extends Entidade, X extends Filtro> implements Serializable {

	private static final long serialVersionUID = 1L;
	private GenericDAO<T> dao;
	private EspecificaCriteria<X> filtroCriteria;

	public PaginacaoDaoUtil(GenericDAO<T> dao, EspecificaCriteria<X> filtroCriteria) {
		this.dao = dao;
		this.filtroCriteria = filtroCriteria;
	}

	public int quantidadeFiltrados(X filtro) {
		Criteria criteria = dao.criteriaHibernate();
		criteria = filtroCriteria.especificadorDeCriteria().apply(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	public List<T> retornaFiltrados(X filtro) {
		Criteria criteria = dao.criteriaHibernate();
		criteria = filtroCriteria.especificadorDeCriteria().apply(filtro, criteria);
		return filtrarResultados(filtro, criteria);
	}

	@SuppressWarnings("unchecked")
	private List<T> filtrarResultados(Filtro filtro, Criteria criteria) {
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());

		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}

		return criteria.list();
	}

}
