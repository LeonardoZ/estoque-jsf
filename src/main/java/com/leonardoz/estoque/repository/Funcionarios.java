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

import com.leonardoz.estoque.model.Funcionario;
import com.leonardoz.estoque.util.Transactional;

public class Funcionarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public void guardarFuncionario(Funcionario funcionario) {
		if (funcionario.getId() != null) {
			manager.merge(funcionario);
		} else {
			manager.persist(funcionario);
		}
		manager.flush();
	}

	@Transactional
	public void removerFuncionario(long idDaFuncionario) {
		Funcionario funcionario = recuperaFuncionario(idDaFuncionario).orElseThrow(EntityNotFoundException::new);
		manager.remove(funcionario);
	}

	public Optional<Funcionario> recuperaFuncionario(Long idDaFuncionario) {
		Optional<Funcionario> optional = Optional.ofNullable(manager.find(Funcionario.class, idDaFuncionario));
		optional.ifPresent(f -> f.iniciarCampos());
		return optional;
	}

	public List<Funcionario> recuperarFuncionarios() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Funcionario> query = builder.createQuery(Funcionario.class);
		Root<Funcionario> root = query.from(Funcionario.class);
		query.select(root).orderBy(builder.asc(root.get("nome")));
		List<Funcionario> resultado = manager.createQuery(query).getResultList();
		resultado.forEach(f -> f.iniciarCampos());
		return resultado;
	}

}
