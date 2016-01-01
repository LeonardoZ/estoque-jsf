package com.leonardoz.estoque.produto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.leonardoz.estoque.infraestrutura.jpa.EspecificaCriteria;
import com.leonardoz.estoque.infraestrutura.jpa.GenericDAO;
import com.leonardoz.estoque.infraestrutura.jpa.PaginacaoDaoUtil;
import com.leonardoz.estoque.infraestrutura.jpa.Transactional;

public class ProdutosJPA implements Serializable, Produtos, EspecificaCriteria<FiltroProduto> {

	private static final long serialVersionUID = 1L;

	private GenericDAO<Produto> dao;
	private PaginacaoDaoUtil<Produto, FiltroProduto> paginacaoUtil;

	@Inject
	public ProdutosJPA(GenericDAO<Produto> dao) {
		this.dao = dao;
		this.dao.configurarClasse(Produto.class);
		this.paginacaoUtil = new PaginacaoDaoUtil<>(dao, this);
	}

	@Override
	@Transactional
	public void guardarProduto(Produto produto) {
		dao.salvar(produto);
	}

	@Override
	@Transactional
	public void removerProduto(long idProduto) {
		dao.remover(idProduto);
	}

	@Override
	public Optional<Produto> recuperarProduto(long idProduto) {
		return dao.recuperarEntidade(idProduto);
	}

	@Override
	public List<Produto> recuperarProdutos() {
		CriteriaBuilder builder = dao.getManager().getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("descricao")));
		return dao.getManager().createQuery(query).getResultList();
	}

	@Override
	public List<Produto> filtrados(FiltroProduto filtro) {
		return paginacaoUtil.retornaFiltrados(filtro);
	}

	@Override
	public int quantosForamFiltrados(FiltroProduto filtro) {
		return paginacaoUtil.quantidadeFiltrados(filtro);
	}

	public BiFunction<FiltroProduto, Criteria, Criteria> especificadorDeCriteria() {
		return (filtro, criteria) -> {
			if (filtro.getDescricao() != null && !filtro.getDescricao().isEmpty()) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			return criteria;
		};
	}

}
