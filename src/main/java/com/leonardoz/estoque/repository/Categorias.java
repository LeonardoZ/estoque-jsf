package com.leonardoz.estoque.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.leonardoz.estoque.model.Categoria;
import com.leonardoz.estoque.util.Transactional;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Session sessao;

	@Transactional
	public void guardarCategoria(Categoria categoria) {
		if (categoria.getId() != null) {
			sessao.merge(categoria);
		} else {
			sessao.persist(categoria);
		}
		sessao.flush();
	}

	@Transactional
	public void removerCategoria(long idDaCategoria) {
		Categoria categoria = recuperaCategoria(idDaCategoria).orElseThrow(EntityNotFoundException::new);
		sessao.delete(categoria);
	}

	public Optional<Categoria> recuperaCategoria(Long idDaCategoria) {
		return Optional.ofNullable((Categoria) sessao.get(Categoria.class, idDaCategoria));
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> recuperarCategorias() {
		Criteria criteria = sessao.createCriteria(Categoria.class);
		criteria.addOrder(Order.asc("descricao"));
		return criteria.list();
	}

}
