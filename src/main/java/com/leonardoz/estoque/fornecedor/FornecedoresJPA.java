package com.leonardoz.estoque.fornecedor;

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
import com.leonardoz.estoque.pessoa.FiltroPessoaJuridica;

public class FornecedoresJPA implements Serializable, Fornecedores, EspecificaCriteria<FiltroPessoaJuridica> {

    private static final long serialVersionUID = 1L;

    private GenericDAO<Fornecedor> dao;
    private PaginacaoDaoUtil<Fornecedor, FiltroPessoaJuridica> paginacao;

    @Inject
    public FornecedoresJPA(GenericDAO<Fornecedor> dao) {
	this.dao = dao;
	this.dao.configurarClasse(Fornecedor.class);
	this.paginacao = new PaginacaoDaoUtil<>(dao, this);
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
	entidade.ifPresent(f -> f.iniciarCampos());
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
	resultado.forEach(Fornecedor::iniciarCampos);
	return resultado;
    }

    @Override
    public int quantosForamFiltrados(FiltroPessoaJuridica filtro) {
	return paginacao.quantidadeFiltrados(filtro);
    }

    @Override
    public List<Fornecedor> filtrados(FiltroPessoaJuridica filtro) {
	return paginacao.retornaFiltrados(filtro);
    }

    @Override
    public BiFunction<FiltroPessoaJuridica, Criteria, Criteria> especificadorDeCriteria() {
	return (filtro, criteria) -> {
	    String nome = filtro.getNomeFantasia();
	    if (nome != null && !nome.isEmpty()) {
		criteria.add(Restrictions.ilike("nomeFantasia", nome, MatchMode.ANYWHERE));
	    }
	    return criteria;
	};
    }

}
