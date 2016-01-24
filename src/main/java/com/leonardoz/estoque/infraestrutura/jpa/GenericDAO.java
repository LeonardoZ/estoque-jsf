package com.leonardoz.estoque.infraestrutura.jpa;

import java.io.Serializable;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.leonardoz.estoque.modelo.entidade.Entidade;

public class GenericDAO<T extends Entidade> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    private Class<T> classe;

    public GenericDAO() {

    }

    public Class<T> getClasse() {
	return classe;
    }

    public void salvar(T entidade) {
	if (entidade != null && entidade.getId() != null) {
	    manager.merge(entidade);
	} else {
	    manager.persist(entidade);
	}
	manager.flush();
    }

    public void remover(Long id) {
	T entidade = recuperarEntidade(id).orElseThrow(EntityNotFoundException::new);
	manager.remove(entidade);
    }

    public Optional<T> recuperarEntidade(long id) {
	return Optional.ofNullable((T) manager.find(classe, id));
    }

    public EntityManager getManager() {
	return manager;
    }

    public Session sessao() {
	return manager.unwrap(Session.class);
    }

    public Criteria criteriaHibernate() {
	return manager.unwrap(Session.class).createCriteria(classe);
    }

    public void configurarClasse(Class<T> classe) {
	this.classe = classe;
    }

}
