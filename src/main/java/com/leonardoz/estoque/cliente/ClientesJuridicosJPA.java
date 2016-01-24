package com.leonardoz.estoque.cliente;

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

public class ClientesJuridicosJPA implements ClientesJuridicos, EspecificaCriteria<FiltroPessoaJuridica> {

    private static final long serialVersionUID = 1L;
    private GenericDAO<ClienteJuridico> dao;
    private PaginacaoDaoUtil<ClienteJuridico, FiltroPessoaJuridica> paginacao;

    @Inject
    public ClientesJuridicosJPA(GenericDAO<ClienteJuridico> dao) {
	this.dao = dao;
	this.dao.configurarClasse(ClienteJuridico.class);
	this.paginacao = new PaginacaoDaoUtil<>(dao, this);
    }

    @Override
    @Transactional
    public void guardarClienteJuridico(ClienteJuridico cliente) {
	dao.salvar(cliente);
    }

    @Override
    @Transactional
    public void removerClienteJuridico(long idDoClienteJuridico) {
	dao.remover(idDoClienteJuridico);
    }

    @Override
    public Optional<ClienteJuridico> recuperaClienteJuridico(Long idDoClienteJuridico) {
	return dao.recuperarEntidade(idDoClienteJuridico);
    }

    @Override
    public List<ClienteJuridico> recuperarClientesJuridicos() {
	EntityManager manager = dao.getManager();
	CriteriaBuilder builder = manager.getCriteriaBuilder();
	CriteriaQuery<ClienteJuridico> query = builder.createQuery(ClienteJuridico.class);
	Root<ClienteJuridico> root = query.from(ClienteJuridico.class);
	query.select(root).orderBy(builder.asc(root.get("nomeFantasia")));
	List<ClienteJuridico> resultado = manager.createQuery(query).getResultList();
	return resultado;
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

    @Override
    public int quantosForamFiltrados(FiltroPessoaJuridica filtro) {
	return paginacao.quantidadeFiltrados(filtro);
    }

    @Override
    public List<ClienteJuridico> filtrados(FiltroPessoaJuridica filtro) {
	return paginacao.retornaFiltrados(filtro);
    }

}
