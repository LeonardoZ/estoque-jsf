package com.leonardoz.estoque.pessoa.funcionario;

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

public class FuncionariosJPA implements Serializable, Funcionarios {

	private static final long serialVersionUID = 1L;

	private GenericDAO<Funcionario> dao;

	@Inject
	public FuncionariosJPA(GenericDAO<Funcionario> dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public void guardarFuncionario(Funcionario funcionario) {
		dao.salvar(funcionario);
	}

	@Override
	@Transactional
	public void removerFuncionario(long idDoFuncionario) {
		dao.remover(idDoFuncionario);
	}

	@Override
	public Optional<Funcionario> recuperaFuncionario(Long idDoFuncionario) {
		Optional<Funcionario> entidade = dao.recuperarEntidade(idDoFuncionario);
		entidade.ifPresent(f -> f.iniciarCampos());
		return entidade;
	}

	@Override
	public List<Funcionario> recuperarFuncionarios() {
		EntityManager manager = dao.getManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Funcionario> query = builder.createQuery(Funcionario.class);
		Root<Funcionario> root = query.from(Funcionario.class);
		query.select(root).orderBy(builder.asc(root.get("nome")));
		List<Funcionario> resultado = manager.createQuery(query).getResultList();
		resultado.forEach(f -> f.iniciarCampos());
		return resultado;
	}

}
