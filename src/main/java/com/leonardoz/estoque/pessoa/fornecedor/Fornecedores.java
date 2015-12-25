package com.leonardoz.estoque.pessoa.fornecedor;

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

public class Fornecedores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public void guardarFornecedor(Fornecedor fornecedor) {
		fornecedor.configurarDetalhes();
		if (fornecedor.getId() != null) {
			manager.merge(fornecedor);
		} else {
			manager.persist(fornecedor);
		}
		manager.flush();
	}

	@Transactional
	public void removerFornecedor(long idDaFornecedor) {
		Fornecedor fornecedor = recuperaFornecedor(idDaFornecedor).orElseThrow(EntityNotFoundException::new);
		manager.remove(fornecedor);
	}

	public Optional<Fornecedor> recuperaFornecedor(Long idDaFornecedor) {
		Optional<Fornecedor> optional = Optional.ofNullable(manager.find(Fornecedor.class, idDaFornecedor));
		optional.ifPresent(f -> f.carregarDetalhes());
		return optional;
	}

	public List<Fornecedor> recuperarFornecedores() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Fornecedor> query = builder.createQuery(Fornecedor.class);
		Root<Fornecedor> root = query.from(Fornecedor.class);
		query.select(root).orderBy(builder.asc(root.get("nomeFantasia")));
		List<Fornecedor> resultado = manager.createQuery(query).getResultList();
		resultado.forEach(f -> f.carregarDetalhes());
		return resultado;
	}

}
