package com.leonardoz.estoque.localizacao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Localizacoes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Estado recuperaEstado(long id) {
	return (Estado) manager.find(Estado.class, id);
    }

    public Cidade recuperaCidade(long id) {
	return (Cidade) manager.find(Cidade.class, id);
    }

    public Set<Estado> selecionarTodosEstados() {
	Pais brasil = (Pais) manager.find(Pais.class, 1l);
	return brasil.getEstados();
    }

    public List<Cidade> buscaCidadesPorEstado(Estado estado) {
	CriteriaBuilder builder = manager.getCriteriaBuilder();
	CriteriaQuery<Cidade> query = builder.createQuery(Cidade.class);
	Root<Cidade> from = query.from(Cidade.class);
	query.select(from).where(builder.equal(from.get("estado"), estado));
	return manager.createQuery(query).getResultList();
    }
}
