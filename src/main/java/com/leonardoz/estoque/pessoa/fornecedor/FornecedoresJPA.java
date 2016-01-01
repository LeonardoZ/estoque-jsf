package com.leonardoz.estoque.pessoa.fornecedor;

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

public class FornecedoresJPA implements Serializable, Fornecedores {

	private static final long serialVersionUID = 1L;

	private GenericDAO<Fornecedor> dao;

	@Inject
	public FornecedoresJPA(GenericDAO<Fornecedor> dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public void guardarFornecedor(Fornecedor fornecedor) {
		dao.salvar(fornecedor);
	}

	@Override
	@Transactional
	public void removerFornecedor(long idDoFornecedor) {
		dao.remover(idDoFornecedor);
	}

	@Override
	public Optional<Fornecedor> recuperaFornecedor(Long idDoFornecedor) {
		Optional<Fornecedor> entidade = dao.recuperarEntidade(idDoFornecedor);
		entidade.ifPresent(f -> f.carregarDetalhes());
		return entidade;
	}

	@Override
	public List<Fornecedor> recuperarFornecedores() {
		EntityManager manager = dao.getManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Fornecedor> query = builder.createQuery(Fornecedor.class);
		Root<Fornecedor> root = query.from(Fornecedor.class);
		query.select(root).orderBy(builder.asc(root.get("nomeFantasia")));
		List<Fornecedor> resultado = manager.createQuery(query).getResultList();
		resultado.forEach(f -> f.carregarDetalhes());
		return resultado;
	}

}
