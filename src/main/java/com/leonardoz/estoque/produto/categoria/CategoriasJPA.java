package com.leonardoz.estoque.produto.categoria;

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

public class CategoriasJPA implements Serializable, Categorias {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Override
	@Transactional
	public void guardarCategoria(Categoria categoria) {
		if (categoria != null && categoria.getId() != null) {
			manager.merge(categoria);
		} else {
			manager.persist(categoria);
		}
		manager.flush();
	}

	@Override
	@Transactional
	public void removerCategoria(long idDaCategoria) {
		Categoria categoria = recuperarCategoria(idDaCategoria).orElseThrow(EntityNotFoundException::new);
		manager.remove(categoria);
	}

	@Override
	public Optional<Categoria> recuperarCategoria(long idDaCategoria) {
		return Optional.ofNullable((Categoria) manager.find(Categoria.class, idDaCategoria));
	}

	@Override
	public List<Categoria> recuperarCategorias() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
		Root<Categoria> root = query.from(Categoria.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("descricao")));
		return manager.createQuery(query).getResultList();
	}

}
