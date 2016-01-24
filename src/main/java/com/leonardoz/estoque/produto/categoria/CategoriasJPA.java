package com.leonardoz.estoque.produto.categoria;

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

public class CategoriasJPA implements Serializable, Categorias {

    private static final long serialVersionUID = 1L;
    private GenericDAO<Categoria> dao;

    @Inject
    public CategoriasJPA(GenericDAO<Categoria> dao) {
	this.dao = dao;
	this.dao.configurarClasse(Categoria.class);
    }

    @Override
    @Transactional
    public void guardarCategoria(Categoria categoria) {
	dao.salvar(categoria);
    }

    @Override
    @Transactional
    public void removerCategoria(long idDaCategoria) {
	dao.remover(idDaCategoria);
    }

    @Override
    public Optional<Categoria> recuperarCategoria(long idDaCategoria) {
	return dao.recuperarEntidade(idDaCategoria);
    }

    @Override
    public List<Categoria> recuperarCategorias() {
	EntityManager manager = dao.getManager();
	CriteriaBuilder builder = manager.getCriteriaBuilder();
	CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
	Root<Categoria> root = query.from(Categoria.class);
	query.select(root);
	query.orderBy(builder.asc(root.get("descricao")));
	return manager.createQuery(query).getResultList();
    }

}
