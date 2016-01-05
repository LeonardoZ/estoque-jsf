package com.leonardoz.estoque.produto.unidade;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.leonardoz.estoque.infraestrutura.jpa.GenericDAO;
import com.leonardoz.estoque.infraestrutura.jpa.Transactional;

public class UnidadesDeMedidaJPA implements Serializable, UnidadesDeMedida {

	private static final long serialVersionUID = 1L;

	private GenericDAO<UnidadeDeMedida> dao;

	@Inject
	public UnidadesDeMedidaJPA(GenericDAO<UnidadeDeMedida> dao) {
		this.dao = dao;
		this.dao.configurarClasse(UnidadeDeMedida.class);
	}

	@Override
	@Transactional
	public void guardarUnidadeDeMedida(UnidadeDeMedida unidade) {
		dao.salvar(unidade);
	}

	@Override
	@Transactional
	public void removerUnidadeDeMedida(long idDaUnidadeDeMedida) {
		dao.remover(idDaUnidadeDeMedida);
	}

	@Override
	public Optional<UnidadeDeMedida> recuperaUnidadeDeMedida(Long idDaUnidadeDeMedida) {
		return dao.recuperarEntidade(idDaUnidadeDeMedida);
	}

	@Override
	public List<UnidadeDeMedida> recuperarUnidadeDeMedidas() {
		EntityManager manager = dao.getManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UnidadeDeMedida> query = builder.createQuery(UnidadeDeMedida.class);
		Root<UnidadeDeMedida> root = query.from(UnidadeDeMedida.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("descricao")));
		return manager.createQuery(query).getResultList();
	}

}
