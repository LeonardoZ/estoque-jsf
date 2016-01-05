package com.leonardoz.estoque.pessoa.cliente;

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

public class ClientesFisicosJPA implements ClientesFisicos, EspecificaCriteria<FiltroPessoaFisica>  {

	private static final long serialVersionUID = 1L;
	private GenericDAO<ClienteFisico> dao;
	private PaginacaoDaoUtil<ClienteFisico, FiltroPessoaFisica> paginacao;

	@Inject
	public ClientesFisicosJPA(GenericDAO<ClienteFisico> dao) {
		this.dao = dao;
		this.dao.configurarClasse(ClienteFisico.class);
		this.paginacao = new PaginacaoDaoUtil<>(dao, this);
	}

	@Transactional
	@Override
	public void guardarClienteFisico(ClienteFisico cliente) {
		dao.salvar(cliente);
	}

	@Transactional
	@Override
	public void removerClienteFisico(long idDoClienteFisico) {
		dao.remover(idDoClienteFisico);
	}

	@Override
	public Optional<ClienteFisico> recuperaClienteFisico(Long idDoClienteFisico) {
		return dao.recuperarEntidade(idDoClienteFisico);
	}

	@Override
	public List<ClienteFisico> recuperarClientesFisicos() {
		EntityManager manager = dao.getManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ClienteFisico> query = builder.createQuery(ClienteFisico.class);
		Root<ClienteFisico> root = query.from(ClienteFisico.class);
		query.select(root).orderBy(builder.asc(root.get("nome")));
		List<ClienteFisico> resultado = manager.createQuery(query).getResultList();
		return resultado;
	}
	

	@Override
	public int quantosForamFiltrados(FiltroPessoaFisica filtro) {
		return paginacao.quantidadeFiltrados(filtro);
	}

	@Override
	public List<ClienteFisico> filtrados(FiltroPessoaFisica filtro) {
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
