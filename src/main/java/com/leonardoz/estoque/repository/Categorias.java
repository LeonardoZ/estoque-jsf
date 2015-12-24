package com.leonardoz.estoque.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.leonardoz.estoque.model.Categoria;
import com.leonardoz.estoque.util.Transactional;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public void guardarCategoria(Categoria categoria) {
		if (categoria != null && categoria.getId() != null) {
			manager.merge(categoria);
		} else {
			System.out.println("Salvou pow!");
			manager.persist(categoria);
		}
		manager.flush();
	}

	@Transactional
	public void removerCategoria(long idDaCategoria) {
		Categoria categoria = recuperaCategoria(idDaCategoria).orElseThrow(EntityNotFoundException::new);
		manager.remove(categoria);
	}

	public Optional<Categoria> recuperaCategoria(Long idDaCategoria) {
		return Optional.ofNullable((Categoria) manager.find(Categoria.class, idDaCategoria));
	}

	public List<Categoria> recuperarCategorias() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
		Root<Categoria> root = query.from(Categoria.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("descricao")));
		return manager.createQuery(query).getResultList();
	}

}
