package com.leonardoz.estoque.funcionario;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
import com.leonardoz.estoque.pessoa.FiltroPessoaFisica;

public class FuncionariosJPA implements Serializable, Funcionarios, EspecificaCriteria<FiltroPessoaFisica> {

    private static final long serialVersionUID = 1L;
    private GenericDAO<Funcionario> dao;
    private PaginacaoDaoUtil<Funcionario, FiltroPessoaFisica> paginacao;

    @Inject
    public FuncionariosJPA(GenericDAO<Funcionario> dao) {
	this.dao = dao;
	this.dao.configurarClasse(Funcionario.class);
	this.paginacao = new PaginacaoDaoUtil<>(dao, this);
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

    @Override
    public int quantosForamFiltrados(FiltroPessoaFisica filtro) {
	return paginacao.quantidadeFiltrados(filtro);
    }

    @Override
    public List<Funcionario> filtrados(FiltroPessoaFisica filtro) {
	return paginacao.retornaFiltrados(filtro);
    }

    @Override
    public BiFunction<FiltroPessoaFisica, Criteria, Criteria> especificadorDeCriteria() {
	return (filtro, criteria) -> {
	    String nome = filtro.getNome();
	    if (nome != null && !nome.isEmpty()) {
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
	    }
	    return criteria;
	};
    }

}
