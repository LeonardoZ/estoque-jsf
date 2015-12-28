package com.leonardoz.estoque.produto.unidade;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.leonardoz.estoque.infraestrutura.Transactional;

public class UnidadesDeMedidaJPA implements Serializable, UnidadesDeMedida {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Override
	@Transactional
	public void guardarUnidadeDeMedida(UnidadeDeMedida unidade) {
		if (unidade != null && unidade.getId() != null) {
			manager.merge(unidade);
		} else {
			System.out.println("Salvou pow!");
			manager.persist(unidade);
		}
		manager.flush();
	}

	@Override
	@Transactional
	public void removerUnidadeDeMedida(long idDaUnidadeDeMedida) {
		UnidadeDeMedida unidade = recuperaUnidadeDeMedida(idDaUnidadeDeMedida)
				.orElseThrow(EntityNotFoundException::new);
		manager.remove(unidade);
	}

	@Override
	public Optional<UnidadeDeMedida> recuperaUnidadeDeMedida(Long idDaUnidadeDeMedida) {
		return Optional.ofNullable(manager.find(UnidadeDeMedida.class, idDaUnidadeDeMedida));
	}

	@Override
	public List<UnidadeDeMedida> recuperarUnidadeDeMedidas() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UnidadeDeMedida> query = builder.createQuery(UnidadeDeMedida.class);
		Root<UnidadeDeMedida> root = query.from(UnidadeDeMedida.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("descricao")));
		return manager.createQuery(query).getResultList();
	}

}
