package com.leonardoz.estoque.produto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.leonardoz.estoque.infraestrutura.Transactional;

public class ProdutosJPA implements Serializable, Produtos {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Override
	@Transactional
	public void guardarProduto(Produto produto) {
		if (produto != null && produto.getId() != null) {
			manager.merge(produto);
		} else {
			manager.persist(produto);
		}
		manager.flush();
	}

	@Override
	@Transactional
	public void removerProduto(long idDaProduto) {
		Produto produto = recuperarProduto(idDaProduto).orElseThrow(EntityNotFoundException::new);
		manager.remove(produto);
	}

	@Override
	public Optional<Produto> recuperarProduto(long idDaProduto) {
		return Optional.ofNullable((Produto) manager.find(Produto.class, idDaProduto));
	}

	@Override
	public List<Produto> recuperarProdutos() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("descricao")));
		return manager.createQuery(query).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(FiltroProduto filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);

		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());

		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}

		return criteria.list();
	}

	public int quantidadeFiltrados(FiltroProduto filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);

		criteria.setProjection(Projections.rowCount());

		return ((Number) criteria.uniqueResult()).intValue();
	}

	private Criteria criarCriteriaParaFiltro(FiltroProduto filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		if (filtro.getDescricao() != null && !filtro.getDescricao().isEmpty()) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}

		return criteria;
	}

}
